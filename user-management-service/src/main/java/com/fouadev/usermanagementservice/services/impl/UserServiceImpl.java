package com.fouadev.usermanagementservice.services.impl;

import com.fouadev.usermanagementservice.dto.AppUserDTO;
import com.fouadev.usermanagementservice.entities.AppRole;
import com.fouadev.usermanagementservice.entities.AppUser;
import com.fouadev.usermanagementservice.repositories.PermissionRepository;
import com.fouadev.usermanagementservice.repositories.RoleRepository;
import com.fouadev.usermanagementservice.repositories.UserRepository;
import com.fouadev.usermanagementservice.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    private Keycloak keycloak;
    private final String realm = "bank-app";

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, Keycloak keycloak) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.keycloak = keycloak;
    }

    @Override
    public void syncUsersFromKeycloak() {

        List<UserRepresentation> keycloakUsers = keycloak.realm(realm).users().list();

        for (UserRepresentation userRep : keycloakUsers) {
            AppUser user = new AppUser();

            user.setId(userRep.getId());
            user.setUsername(userRep.getUsername());
            user.setEmail(userRep.getEmail());
            user.setFirstName(userRep.getFirstName());
            user.setLastName(userRep.getLastName());


            List<RoleRepresentation> realmRoles = keycloak.realm(realm)
                    .users().get(userRep.getId()).roles().realmLevel().listEffective();

            Set<AppRole> userRoles = new HashSet<>();


            for (RoleRepresentation roleRep : realmRoles) {

                AppRole role = roleRepository.findById(roleRep.getId())
                        .orElseGet(() -> {
                            AppRole newRole = new AppRole();
                            newRole.setId(roleRep.getId());
                            newRole.setName(roleRep.getName());
                            return roleRepository.save(newRole);
                        });

                userRoles.add(role);
            }

            user.setRoles(userRoles);


            userRepository.save(user);
        }
    }

    @Override
    public AppUserDTO createUser(AppUserDTO appUserDTO) {

        // create user in keycloak
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(appUserDTO.getUsername());
        userRep.setEmail(appUserDTO.getEmail());
        userRep.setFirstName(appUserDTO.getFirstName());
        userRep.setLastName(appUserDTO.getLastName());
        userRep.setEnabled(true);

        // create password
        CredentialRepresentation credentialRep = new CredentialRepresentation();
        credentialRep.setTemporary(false);
        credentialRep.setType(CredentialRepresentation.PASSWORD);
        credentialRep.setValue(appUserDTO.getPassword());
        userRep.setCredentials(List.of(credentialRep));

        Response response = keycloak.realm(realm).users().create(userRep);
        log.info("Response status: {}", response.getStatus());

        if (response.getStatus() != 201) {
            //throw new RuntimeException("Failed to create user in keycloak");
            log.error("Failed to create user in keycloak");
        }

        // Extract user id
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // save user in database
        AppUser user = AppUser.builder()
                .email(appUserDTO.getEmail())
                .firstName(appUserDTO.getFirstName())
                .lastName(appUserDTO.getLastName())
                .username(appUserDTO.getUsername())
                .id(userId)
                .roles(new HashSet<>())
                .build();

        userRepository.save(user);

        // return user
        return AppUserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

    }
}