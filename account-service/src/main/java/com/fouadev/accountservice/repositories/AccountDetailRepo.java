package com.fouadev.accountservice.repositories;

import com.fouadev.accountservice.entities.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDetailRepo extends JpaRepository<AccountDetail, String> {
    Optional<AccountDetail> findByAccountNumber(String accountNumber);
}
