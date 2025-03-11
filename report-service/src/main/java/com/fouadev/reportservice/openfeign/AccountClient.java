package com.fouadev.reportservice.openfeign;

import com.fouadev.reportservice.model.AccountDetail;
import com.fouadev.reportservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {
    @GetMapping("/accounts")
    @CircuitBreaker(name="accountService",fallbackMethod = "getDefaultAccounts")
    List<AccountDetail> findAllAccounts();

    default List<AccountDetail> getDefaultAccounts(Exception exception){
        return List.of();
    }
}
