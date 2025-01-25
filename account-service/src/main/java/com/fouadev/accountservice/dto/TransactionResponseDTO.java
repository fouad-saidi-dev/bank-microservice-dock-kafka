package com.fouadev.accountservice.dto;
/*
 Created by : Fouad SAIDI on 25/01/2025
 @author : Fouad SAIDI
 @date : 25/01/2025
 @project : bank-microservice-kafka
*/

import com.fouadev.accountservice.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class TransactionResponseDTO {
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
}