package com.fouadev.usermanagementservice.dto;

import com.fouadev.usermanagementservice.entities.AppPermission;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppRoleDTO {

    private String id;
    private String name;
    private Set<AppPermission> permissions;
}