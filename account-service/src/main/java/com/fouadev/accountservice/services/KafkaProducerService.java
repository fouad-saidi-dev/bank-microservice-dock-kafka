package com.fouadev.accountservice.services;


import com.fouadev.accountservice.event.AccountEvent;
import com.fouadev.accountservice.event.TransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, AccountEvent> kafkaTemplate;
    private final KafkaTemplate<String, TransactionEvent> eventKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, AccountEvent> kafkaTemplate, KafkaTemplate<String, TransactionEvent> eventKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.eventKafkaTemplate = eventKafkaTemplate;
    }

    public void sendAccountMessage(AccountEvent event){
        log.info(event.getAccountNumber(),"account number {}");
        kafkaTemplate.send("account-topic", event);
    }

    public void sendTrxMessage(TransactionEvent event){
        log.info("transaction type {}",event.getTransactionType());
        eventKafkaTemplate.send("trx-topic", event);
    }
}