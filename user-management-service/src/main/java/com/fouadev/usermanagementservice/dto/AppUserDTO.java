package com.fouadev.usermanagementservice.dto;

import lombok.*;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AppUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}