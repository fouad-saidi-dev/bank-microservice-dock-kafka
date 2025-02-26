package com.fouadev.notificationservice.mapper;

import com.fouadev.notificationservice.dto.NotificationDTO;
import com.fouadev.notificationservice.entities.Notification;
import org.springframework.stereotype.Component;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Component
public class NotificationMapper {
    public NotificationDTO fromNotification(Notification notification) {
        return NotificationDTO.builder()
                .message(notification.getMessage())
                .timestamp(notification.getTimestamp())
                .build();
    }

    public Notification fromNotificationDTO(NotificationDTO notificationDTO) {
        return Notification.builder()
                .message(notificationDTO.message())
                .timestamp(notificationDTO.timestamp())
                .build();
    }
}