package com.fouadev.usermanagementservice.mapper;

import com.fouadev.usermanagementservice.dto.AppRoleDTO;
import com.fouadev.usermanagementservice.dto.AppUserDTO;
import com.fouadev.usermanagementservice.dto.AppUserResponseDTO;
import com.fouadev.usermanagementservice.entities.AppRole;
import com.fouadev.usermanagementservice.entities.AppUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

/*
 Created by : Fouad SAIDI on 17/04/2025
 @author : Fouad SAIDI
 @date : 17/04/2025
 @project : bank-microservice-kafka
*/
@Component
public class UserMapper {

    public AppUser fromUserDTOtoUser(AppUserDTO appUserDTO){
        return AppUser.builder()
                .firstName(appUserDTO.getFirstName())
                .lastName(appUserDTO.getLastName())
                .email(appUserDTO.getEmail())
                .username(appUserDTO.getUsername())
                .password(appUserDTO.getPassword())
                .build();
    }

    public AppUserResponseDTO fromUserToUserDTO(AppUser appUser){
        return AppUserResponseDTO.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .username(appUser.getUsername())
                .roles(appUser.getRoles() != null
                        ? appUser.getRoles().stream()
                        .map(this::fromRoleToDto)
                        .collect(Collectors.toSet())
                        : new HashSet<>())
                .build();
    }

    public AppRoleDTO fromRoleToDto(AppRole role){
        return AppRoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public AppRole fromRoleDTOtoRole(AppRoleDTO role){
        return AppRole.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}