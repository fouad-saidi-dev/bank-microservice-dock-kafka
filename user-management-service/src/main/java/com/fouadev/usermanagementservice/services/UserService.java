package com.fouadev.usermanagementservice.services;

import com.fouadev.usermanagementservice.dto.AppRoleDTO;
import com.fouadev.usermanagementservice.dto.AppUserDTO;

public interface UserService {
    void syncUsersFromKeycloak();
    AppUserDTO createUser(AppUserDTO appUserDTO);
    AppRoleDTO createRole(AppRoleDTO appRoleDTO);
}
