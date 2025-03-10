package com.fouadev.notificationservice.services;

import com.fouadev.customerservice.event.CustomerEvent;
import com.fouadev.notificationservice.entities.Notification;
import com.fouadev.notificationservice.repositories.NotificationRespo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 Created by : Fouad SAIDI on 04/03/2025
 @author : Fouad SAIDI
 @date : 04/03/2025
 @project : bank-microservice-kafka
*/
@Service
public class KafkaConsumerService {

    private NotificationRespo notificationRespo;
    private final List<String> notifications = new ArrayList<>();

    public KafkaConsumerService(NotificationRespo notificationRespo) {
        this.notificationRespo = notificationRespo;
    }

    @KafkaListener(topics = "customers-topic", groupId = "notification-group")
    public void consumeCustomerEvent(CustomerEvent customerEvent) {

        System.out.println("Received event: " + customerEvent);

        String message = "Customer " + customerEvent.getType() + " : " + customerEvent.getName();
        notifications.add(message);

        System.out.println("Notification ajoutée : " + message);

        try {
            Notification notification = Notification.builder()
                    .message(message)
                    .eventId(customerEvent.getId())
                    .build();

            notificationRespo.save(notification);

        } catch (Exception e) {
            System.out.println("Error saving notification: " + e.getMessage());
        }
    }

    public List<Notification> getNotifications() {
        return notificationRespo.findAll();
    }

}