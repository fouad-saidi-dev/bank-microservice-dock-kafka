package com.fouadev.customerservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 Created by : Fouad SAIDI on 26/01/2025
 @author : Fouad SAIDI
 @date : 26/01/2025
 @project : bank-microservice-kafka
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerCreatedEvent {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}