package com.fouadev.usermanagementservice.dto;
/*
 Created by : Fouad SAIDI on 17/04/2025
 @author : Fouad SAIDI
 @date : 17/04/2025
 @project : bank-microservice-kafka
*/

import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserResponseDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<AppRoleDTO> roles;
}