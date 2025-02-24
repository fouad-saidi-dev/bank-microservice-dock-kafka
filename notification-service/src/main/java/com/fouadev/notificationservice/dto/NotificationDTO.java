package com.fouadev.notificationservice.dto;

import lombok.*;

/*
 Created by : Fouad SAIDI on 06/02/2025
 @author : Fouad SAIDI
 @date : 06/02/2025
 @project : bank-microservice-kafka
*/
@Builder
public record NotificationDTO(String title, String content) {
}