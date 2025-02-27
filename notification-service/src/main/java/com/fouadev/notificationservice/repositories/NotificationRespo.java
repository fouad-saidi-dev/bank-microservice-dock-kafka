package com.fouadev.notificationservice.repositories;

import com.fouadev.notificationservice.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRespo extends JpaRepository<Notification, String> {
    boolean existsByEventId(String eventId);
}
