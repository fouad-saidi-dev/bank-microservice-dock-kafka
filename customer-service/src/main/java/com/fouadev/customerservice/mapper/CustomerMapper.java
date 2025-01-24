package com.fouadev.customerservice.mapper;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.entities.Customer;
import com.fouadev.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@Component
public class CustomerMapper {
    private CustomerRepository customerRepository;

    public CustomerMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .build();
    }

    public CustomerDTO fromCustomer(Customer customer) {
        return CustomerDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}