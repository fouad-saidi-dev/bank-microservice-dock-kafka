package com.fouadev.accountservice.mapper;

import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.dto.TransactionDTO;
import com.fouadev.accountservice.dto.TransactionResponseDTO;
import com.fouadev.accountservice.entities.AccountDetail;
import com.fouadev.accountservice.entities.Transaction;
import com.fouadev.accountservice.repositories.AccountDetailRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@Component
public class AccountDetailMapper {


    public AccountDetailDTO toAccountDetailDTO(AccountDetail accountDetail) {
        return AccountDetailDTO.builder()
                .id(accountDetail.getId())
                .accountNumber(accountDetail.getAccountNumber())
                .accountType(accountDetail.getAccountType())
                .balance(accountDetail.getBalance())
                .customerId(accountDetail.getCustomerId())
                .customer(accountDetail.getCustomer())
                .transactions(accountDetail.getTransactions() != null
                        ? accountDetail.getTransactions().stream()
                        .map(this::toTransactionRespDTO)
                        .toList() : null)
                .build();
    }

    public AccountDetail toAccountDetail(AccountDetailDTO accountDetailDTO) {
        return AccountDetail.builder()
                .id(accountDetailDTO.getId())
                .accountNumber(accountDetailDTO.getAccountNumber())
                .accountType(accountDetailDTO.getAccountType())
                .balance(accountDetailDTO.getBalance())
                .customerId(accountDetailDTO.getCustomerId())
                .customer(accountDetailDTO.getCustomer())
                //.transactions(accountDetailDTO.getTransactions())
                .build();
    }

    public Transaction toTransaction(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .amount(transactionDTO.getAmount())
                .transactionType(transactionDTO.getTransactionType())
                .transactionDate(transactionDTO.getTransactionDate())
                .accountDetail(transactionDTO.getAccountDetail())
                .build();
    }

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .transactionDate(transaction.getTransactionDate())
                .accountDetail(transaction.getAccountDetail())
                .build();
    }

    public TransactionResponseDTO toTransactionRespDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .transactionDate(LocalDateTime.now())
                .build();
    }

}