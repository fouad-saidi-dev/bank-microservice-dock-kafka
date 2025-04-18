package com.fouadev.usermanagementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/*
 Created by : Fouad SAIDI on 15/04/2025
 @author : Fouad SAIDI
 @date : 15/04/2025
 @project : bank-microservice-kafka
*/
@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppPermission> permissions;
}