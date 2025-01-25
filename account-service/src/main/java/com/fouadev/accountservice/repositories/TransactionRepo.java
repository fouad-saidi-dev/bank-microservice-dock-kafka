package com.fouadev.accountservice.repositories;

import com.fouadev.accountservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
