package com.fouadev.usermanagementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/
@Entity(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
}