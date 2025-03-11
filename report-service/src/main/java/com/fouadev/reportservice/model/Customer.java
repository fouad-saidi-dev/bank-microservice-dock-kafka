package com.fouadev.reportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}