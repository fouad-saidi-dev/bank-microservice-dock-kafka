package com.fouadev.accountservice.services.impl;
/*
 Created by : Fouad SAIDI on 25/01/2025
 @author : Fouad SAIDI
 @date : 25/01/2025
 @project : bank-microservice-kafka
*/

import com.fouadev.accountservice.dto.TransactionDTO;
import com.fouadev.accountservice.dto.TransactionResponseDTO;
import com.fouadev.accountservice.entities.Transaction;
import com.fouadev.accountservice.mapper.AccountDetailMapper;
import com.fouadev.accountservice.repositories.TransactionRepo;
import com.fouadev.accountservice.services.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private AccountDetailMapper mapper;
    private TransactionRepo transactionRepo;

    public TransactionServiceImpl(AccountDetailMapper mapper, TransactionRepo transactionRepo) {
        this.mapper = mapper;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public TransactionResponseDTO addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = mapper.toTransaction(transactionDTO);
        Transaction savedTransaction = transactionRepo.save(transaction);
        return mapper.toTransactionRespDTO(savedTransaction);
    }

    @Override
    public TransactionResponseDTO getTransaction(Long id) {
        Transaction transaction = transactionRepo.findById(id).get();
        return mapper.toTransactionRespDTO(transaction);
    }

    @Override
    public List<TransactionResponseDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream().map(mapper::toTransactionRespDTO).toList();
    }

    @Override
    public void deleteTransaction(Long id) {

    }
}