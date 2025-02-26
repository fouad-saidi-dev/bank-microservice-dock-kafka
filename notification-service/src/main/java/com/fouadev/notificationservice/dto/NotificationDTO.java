package com.fouadev.notificationservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Builder
public record NotificationDTO(String message, LocalDateTime timestamp) {
}