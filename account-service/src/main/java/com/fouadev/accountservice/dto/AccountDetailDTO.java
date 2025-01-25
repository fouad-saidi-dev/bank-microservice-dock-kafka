package com.fouadev.accountservice.dto;
/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/

import com.fouadev.accountservice.enums.AccountType;
import com.fouadev.accountservice.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AccountDetailDTO {

    private String id;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private Customer customer;
    private Long customerId;
    private List<TransactionResponseDTO> transactions;

}