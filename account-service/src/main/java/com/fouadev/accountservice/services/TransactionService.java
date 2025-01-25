package com.fouadev.accountservice.services;

import com.fouadev.accountservice.dto.TransactionDTO;
import com.fouadev.accountservice.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO addTransaction(TransactionDTO transactionDTO);

    TransactionResponseDTO getTransaction(Long id);

    List<TransactionResponseDTO> getAllTransactions();

    void deleteTransaction(Long id);
}
