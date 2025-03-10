package com.fouadev.customerservice.services;

import com.fouadev.customerservice.event.CustomerEvent;
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

    private final KafkaTemplate<String, CustomerEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, CustomerEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CustomerEvent event){
        System.out.println("Sent event : " + event.getName());
        kafkaTemplate.send("customers-topic", event);
    }
}