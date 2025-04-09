package com.fouadev.notificationservice.services.impl;

import com.fouadev.notificationservice.dto.NotificationDTO;
import com.fouadev.notificationservice.entities.Notification;
import com.fouadev.notificationservice.mapper.NotificationMapper;
import com.fouadev.notificationservice.repositories.NotificationRespo;
import com.fouadev.notificationservice.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationMapper notificationMapper;
    private NotificationRespo notificationRespo;

    public NotificationServiceImpl(NotificationMapper notificationMapper, NotificationRespo notificationRespo) {
        this.notificationMapper = notificationMapper;
        this.notificationRespo = notificationRespo;
    }

    @Override
    public void sendNotification(String title, String content) {
    }

    @Override
    public List<NotificationDTO> displayNotifications() {
        List<Notification> notifications = notificationRespo.findAll();
        return notifications.stream().map(notificationMapper::fromNotification).toList();
    }
}