package com.fouadev.accountservice.repositories;

import com.fouadev.accountservice.entities.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailRepo extends JpaRepository<AccountDetail, String> {
}
