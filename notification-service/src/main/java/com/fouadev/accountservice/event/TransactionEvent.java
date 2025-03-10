package com.fouadev.accountservice.event;

import com.fouadev.accountservice.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private EventType type;
}