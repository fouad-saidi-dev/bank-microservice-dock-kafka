package com.fouadev.notificationservice.services;

import com.fouadev.accountservice.event.AccountEvent;
import com.fouadev.accountservice.event.TransactionEvent;
import com.fouadev.customerservice.event.CustomerEvent;
import com.fouadev.notificationservice.entities.Notification;
import com.fouadev.notificationservice.repositories.NotificationRespo;
import jakarta.transaction.Transactional;
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
@Transactional
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

    @KafkaListener(topics = "account-topic")
    public void consumeCustomerEvent(AccountEvent accountEvent) {

        System.out.println("Received event: " + accountEvent);

        String message = "Account " + accountEvent.getType() + " : " + accountEvent.getAccountNumber();
        notifications.add(message);

        System.out.println("Notification ajoutée : " + message);

        try {
            Notification notification = Notification.builder()
                    .message(message)
                    .build();

            notificationRespo.save(notification);

        } catch (Exception e) {
            System.out.println("Error saving notification: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "trx-topic")
    public void consumeTrxEvent(TransactionEvent transactionEvent) {

        System.out.println("Received event: " + transactionEvent);

        String message = "Transaction " + transactionEvent.getType() + " : " + transactionEvent.getTransactionDate();
        notifications.add(message);

        System.out.println("Notification ajoutée : " + message);

        try {
            Notification notification = Notification.builder()
                    .message(message)
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