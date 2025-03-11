package com.fouadev.reportservice.model;


import com.fouadev.reportservice.enums.AccountType;
import lombok.*;

import java.util.List;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDetail {

    private String id;
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private Customer customer;
}