package com.fouadev.customerservice.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
 Created by : Fouad SAIDI on 26/01/2025
 @author : Fouad SAIDI
 @date : 26/01/2025
 @project : bank-microservice-kafka
*/
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object message){
        kafkaTemplate.send(topic, message);
    }
}