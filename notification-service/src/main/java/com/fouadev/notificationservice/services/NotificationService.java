package com.fouadev.notificationservice.services;

import com.fouadev.notificationservice.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    void sendNotification(String title, String content);
    List<NotificationDTO> displayNotifications();
}
