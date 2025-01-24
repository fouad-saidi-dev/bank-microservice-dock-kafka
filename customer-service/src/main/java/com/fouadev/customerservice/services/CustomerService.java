package com.fouadev.customerservice.services;

import com.fouadev.customerservice.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO addCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomer(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomer(Long id);
}
