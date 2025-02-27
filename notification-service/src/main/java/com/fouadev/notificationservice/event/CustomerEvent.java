package com.fouadev.notificationservice.event;
/*
 Created by : Fouad SAIDI on 24/02/2025
 @author : Fouad SAIDI
 @date : 24/02/2025
 @project : bank-microservice-kafka
*/

public record CustomerEvent(String id,String name, String email,EventType type) {
}