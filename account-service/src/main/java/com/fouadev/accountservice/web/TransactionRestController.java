package com.fouadev.accountservice.web;

import com.fouadev.accountservice.dto.TransactionDTO;
import com.fouadev.accountservice.dto.TransactionResponseDTO;
import com.fouadev.accountservice.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 Created by : Fouad SAIDI on 25/01/2025
 @author : Fouad SAIDI
 @date : 25/01/2025
 @project : bank-microservice-kafka
*/
@RestController
@RequestMapping("/transactions")
public class TransactionRestController {
     private TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping
    public List<TransactionResponseDTO> getTransactions() {
        return transactionService.getAllTransactions();
    }

}