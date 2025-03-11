package com.fouadev.reportservice.openfeign;

import com.fouadev.reportservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*
 Created by : Fouad SAIDI on 10/03/2025
 @author : Fouad SAIDI
 @date : 10/03/2025
 @project : bank-microservice-kafka
*/
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/customers")
    @CircuitBreaker(name="customerService",fallbackMethod = "getDefaultCustomers")
    List<Customer> findAllCustomers();

    default List<Customer> getDefaultCustomers(Exception exception){
        return List.of();
    }

}