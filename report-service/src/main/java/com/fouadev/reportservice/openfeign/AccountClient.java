package com.fouadev.reportservice.openfeign;

import com.fouadev.reportservice.model.AccountDetail;
import com.fouadev.reportservice.model.Customer;
import com.fouadev.reportservice.model.Transaction;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {
    @GetMapping("/accounts")
    @CircuitBreaker(name="accountService",fallbackMethod = "getDefaultAccounts")
    List<AccountDetail> findAllAccounts();

    @GetMapping("/transactions")
    @CircuitBreaker(name="accountService",fallbackMethod = "getDefaultTransactions")
    List<Transaction> findAllTransactions();

    default List<AccountDetail> getDefaultAccounts(Exception exception){
        return List.of();
    }

    default List<Transaction> getDefaultTransactions(Exception exception){
        return List.of();
    }
}
