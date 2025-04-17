package com.fouadev.usermanagementservice.web;

import com.fouadev.usermanagementservice.dto.AppPermissionDTO;
import com.fouadev.usermanagementservice.dto.AppRoleDTO;
import com.fouadev.usermanagementservice.dto.AppUserDTO;
import com.fouadev.usermanagementservice.dto.AppUserResponseDTO;
import com.fouadev.usermanagementservice.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sync")
    public String syncUsersFromKeycloak() {
        userService.syncUsersFromKeycloak();
        return "Users successfully synced from Keycloak!";
    }

    @PostMapping("/createUser")
    public AppUserDTO createUser(@RequestBody AppUserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("")
    public List<AppUserResponseDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/roles/createRole")
    public AppRoleDTO createUser(@RequestBody AppRoleDTO appRoleDTO){
        return userService.createRole(appRoleDTO);
    }

    @PostMapping("/permissions/createPermission")
    public AppPermissionDTO createUser(@RequestBody AppPermissionDTO permissionDTO){
        return userService.createPermission(permissionDTO);
    }
}