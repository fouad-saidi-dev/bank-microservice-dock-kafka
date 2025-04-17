package com.fouadev.usermanagementservice.services;

import com.fouadev.usermanagementservice.dto.AppPermissionDTO;
import com.fouadev.usermanagementservice.dto.AppRoleDTO;
import com.fouadev.usermanagementservice.dto.AppUserDTO;
import com.fouadev.usermanagementservice.dto.AppUserResponseDTO;

import java.util.List;

public interface UserService {
    void syncUsersFromKeycloak();
    AppUserDTO createUser(AppUserDTO appUserDTO);
    AppRoleDTO createRole(AppRoleDTO appRoleDTO);
    List<AppUserResponseDTO> getUsers();
    AppPermissionDTO createPermission(AppPermissionDTO appPermissionDTO);
}
