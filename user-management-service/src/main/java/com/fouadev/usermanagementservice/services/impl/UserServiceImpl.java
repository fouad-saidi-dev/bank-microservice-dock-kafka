package com.fouadev.usermanagementservice.services.impl;

import com.fouadev.usermanagementservice.dto.AppPermissionDTO;
import com.fouadev.usermanagementservice.dto.AppRoleDTO;
import com.fouadev.usermanagementservice.dto.AppUserDTO;
import com.fouadev.usermanagementservice.dto.AppUserResponseDTO;
import com.fouadev.usermanagementservice.entities.AppPermission;
import com.fouadev.usermanagementservice.entities.AppRole;
import com.fouadev.usermanagementservice.entities.AppUser;
import com.fouadev.usermanagementservice.mapper.UserMapper;
import com.fouadev.usermanagementservice.repositories.PermissionRepository;
import com.fouadev.usermanagementservice.repositories.RoleRepository;
import com.fouadev.usermanagementservice.repositories.UserRepository;
import com.fouadev.usermanagementservice.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ScopeRepresentation;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, Keycloak keycloak, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.keycloak = keycloak;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
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

//                if(roleRep.isComposite()){
//                    Set<RoleRepresentation> compositeRoles = keycloak.realm(realm)
//                            .roles().get(roleRep.getName()).getRoleComposites();
//
//                    compositeRoles.forEach(composite -> {
//                        var perm = permissionRepository.findById(composite.getId())
//                                .orElseGet(()->{
//                                    var newPer = AppPermission.builder()
//                                            .id(composite.getId())
//                                            .name(composite.getName())
//                                            .build();
//                                    return permissionRepository.save(newPer);
//                                });
//
//                        role.getPermissions().add(perm);
//                    });
//                }

                userRoles.add(role);
            }

            user.setRoles(userRoles);
            userRepository.save(user);
        }
    }

    @Override
    public AppUserDTO createUser(AppUserDTO appUserDTO) {

        if (!appUserDTO.getPassword().equals(appUserDTO.getConfirmPassword())) {
            log.error("Password and confirm password do not match");
            throw new IllegalArgumentException("Password and confirm password must match");
        }

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
        //log.info("Response status: {}", response.getStatus());
        String body = response.readEntity(String.class);
        log.info("Keycloak responded with status {} and body: {}", response.getStatus(), body);

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
                .password(passwordEncoder.encode(appUserDTO.getPassword()))
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

    @Override
    public AppRoleDTO createRole(AppRoleDTO appRoleDTO) {

        RoleRepresentation roleRep = new RoleRepresentation();
        roleRep.setName(appRoleDTO.getName().toUpperCase());
        roleRep.setDescription("Role ..");

        RoleRepresentation existingRole = keycloak.realm(realm).roles().get(roleRep.getName()).toRepresentation();

        try {
            if (existingRole == null) keycloak.realm(realm).roles().create(roleRep);
            log.info("Role '{}' created successfully", appRoleDTO.getName());
        } catch (Exception e) {
            log.error("Error creating role in Keycloak", e);
            throw new RuntimeException("Error creating role: " + e.getMessage());
        }

        AppRole appRole = AppRole.builder()
                .name(appRoleDTO.getName())
                .build();

        roleRepository.save(appRole);

        return AppRoleDTO.builder()
                .name(appRole.getName())
                .build();
    }

    @Override
    public List<AppUserResponseDTO> getUsers() {

        List<AppUser> appUsers = userRepository.findAll();

        return appUsers.stream().map(userMapper::fromUserToUserDTO).toList();
    }

    @Override
    public AppPermissionDTO createPermission(AppPermissionDTO appPermissionDTO) {

        AppPermission appPermission = AppPermission.builder()
                .name(appPermissionDTO.name())
                .description(appPermissionDTO.description())
                .build();

        ClientRepresentation client = keycloak
                .realm(realm)
                .clients()
                .findByClientId("bank-cloud-app")
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found"));

        String clientId = client.getId();

        ResourceRepresentation resRep = new ResourceRepresentation();
        resRep.setName(appPermissionDTO.name());
        resRep.setDisplayName(appPermissionDTO.description());
        resRep.setOwnerManagedAccess(true);
        resRep.setScopes(Set.of(new ScopeRepresentation("default")));

        Response response = keycloak
                .realm(realm)
                .clients()
                .get(clientId)
                .authorization()
                .resources()
                .create(resRep);

        String body = response.readEntity(String.class);

        log.info("Keycloak responded with status {} and body: {}", response.getStatus(), body);

        permissionRepository.save(appPermission);

        return AppPermissionDTO.builder()
                .name(appPermission.getName())
                .description(appPermission.getDescription())
                .build();
    }
}