package com.fouadev.usermanagementservice.dto;


import lombok.Builder;

/*
 Created by : Fouad SAIDI on 17/04/2025
 @author : Fouad SAIDI
 @date : 17/04/2025
 @project : bank-microservice-kafka
*/
@Builder
public record AppPermissionDTO(String id, String name, String description) {
}