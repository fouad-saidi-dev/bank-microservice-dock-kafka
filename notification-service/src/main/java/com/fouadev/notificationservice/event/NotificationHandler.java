package com.fouadev.notificationservice.event;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/*
 Created by : Fouad SAIDI on 24/02/2025
 @author : Fouad SAIDI
 @date : 24/02/2025
 @project : bank-microservice-kafka
*/
@Component
public class NotificationHandler {

    @Bean
    public Consumer<CustomerEvent> customerEventConsumer() {
        System.out.println("NotificationHandler bean created");
        return (event) -> {
            System.out.println("Notification envoyée à l'utilisateur pour le client : " + event.name());
        };
    }
}