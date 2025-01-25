package com.fouadev.accountservice.dto;

import com.fouadev.accountservice.entities.AccountDetail;
import com.fouadev.accountservice.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
 Created by : Fouad SAIDI on 25/01/2025
 @author : Fouad SAIDI
 @date : 25/01/2025
 @project : bank-microservice-kafka
*/

@Getter
@Setter
@Builder
public class TransactionDTO {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private AccountDetail accountDetail;
}