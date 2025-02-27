package com.fouadev.customerservice.web;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@RestController
@RequestMapping("/customers")
public class CustomerControllerRest {
    private CustomerService customerService;

    public CustomerControllerRest(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(customerDTO);
    }
    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }
    @GetMapping("")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}