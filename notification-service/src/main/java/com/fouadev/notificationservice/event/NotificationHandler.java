package com.fouadev.notificationservice.event;

import com.fouadev.notificationservice.entities.Notification;
import com.fouadev.notificationservice.repositories.NotificationRespo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/*
 Created by : Fouad SAIDI on 24/02/2025
 @author : Fouad SAIDI
 @date : 24/02/2025
 @project : bank-microservice-kafka
*/
@Component
public class NotificationHandler {

    List<String> notifications = new ArrayList<>();
    private NotificationRespo notificationRespo;

    public NotificationHandler(NotificationRespo notificationRespo) {
        this.notificationRespo = notificationRespo;
    }

    @Bean
    public Consumer<CustomerEvent> customerEventConsumer() {
        System.out.println("NotificationHandler bean created");
        return (event) -> {
            String message = "Notification envoyée à l'utilisateur pour le client : " + event.name();
            notifications.add(message);
            System.out.println(message);
            Notification notification = Notification
                    .builder()
                    .message(message)
                    .build();
            notificationRespo.save(notification);
        };
    }

    public List<Notification> getNotifications() {
        return notificationRespo.findAll();
    }
}