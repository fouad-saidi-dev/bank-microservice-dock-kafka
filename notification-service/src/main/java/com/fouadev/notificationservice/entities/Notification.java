package com.fouadev.notificationservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Entity(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String message;
    private Long eventId;
    @CreationTimestamp
    private LocalDateTime timestamp;
}