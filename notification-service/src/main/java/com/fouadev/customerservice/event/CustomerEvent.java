package com.fouadev.customerservice.event;
/*
 Created by : Fouad SAIDI on 24/02/2025
 @author : Fouad SAIDI
 @date : 24/02/2025
 @project : bank-microservice-kafka
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEvent{
    private Long id;
    private String name;
    private String email;
    private EventType type;
    private long duration;
}