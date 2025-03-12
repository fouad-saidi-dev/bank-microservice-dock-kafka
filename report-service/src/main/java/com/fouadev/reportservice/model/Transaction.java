package com.fouadev.reportservice.model;


import com.fouadev.reportservice.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 Created by : Fouad SAIDI on 25/01/2025
 @author : Fouad SAIDI
 @date : 25/01/2025
 @project : bank-microservice-kafka
*/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private AccountDetail accountDetail;

    public String getFormattedTransactionDate() {
        return transactionDate != null ? transactionDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : "N/A";
    }
}