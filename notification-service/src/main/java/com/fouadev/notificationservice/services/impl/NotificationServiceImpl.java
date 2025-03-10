package com.fouadev.notificationservice.services.impl;

import com.fouadev.notificationservice.services.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(String title, String content) {
    }
}