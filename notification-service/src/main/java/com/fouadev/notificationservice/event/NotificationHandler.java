package com.fouadev.notificationservice.event;

import com.fouadev.notificationservice.entities.Notification;
import com.fouadev.notificationservice.repositories.NotificationRespo;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
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

/**
 * kafka with retry and backoff
 * with spring retry
 */


//@Component
public class NotificationHandler {

    List<String> notifications = new ArrayList<>();
    private NotificationRespo notificationRespo;

    public NotificationHandler(NotificationRespo notificationRespo) {
        this.notificationRespo = notificationRespo;
    }
//    @Retryable(
//            value = Exception.class,
//            maxAttempts = 5,
//            backoff = @Backoff(delay = 2000, multiplier = 2)
//    )
//    @Bean
//    public Consumer<CustomerEvent> customerEventConsumer() {
//        System.out.println("NotificationHandler bean created");
//        return (event) -> {
//            if (notificationRespo.existsByEventId(event.id())) {
//                System.out.println("Event déjà traité : " + event.id());
//                return;
//            }
//            String message = "Customer "+event.type()+" : "+event.name();
//            notifications.add(message);
//            System.out.println(message);
//            Notification notification = Notification
//                    .builder()
//                    .message(message)
//                    .build();
//            notificationRespo.save(notification);
//        };
//    }

    public List<Notification> getNotifications() {
        return notificationRespo.findAll();
    }
}