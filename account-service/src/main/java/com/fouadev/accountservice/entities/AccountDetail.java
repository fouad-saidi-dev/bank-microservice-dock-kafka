package com.fouadev.accountservice.entities;

import com.fouadev.accountservice.enums.AccountType;
import com.fouadev.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountNumber;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    private Double balance;
    @Transient
    private Customer customer;
    private Long customerId;
    @OneToMany(mappedBy = "accountDetail")
    private List<Transaction> transactions;
}