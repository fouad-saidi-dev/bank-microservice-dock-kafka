package com.fouadev.customerservice.services.impl;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.entities.Customer;
import com.fouadev.customerservice.event.CustomerEvent;
import com.fouadev.customerservice.mapper.CustomerMapper;
import com.fouadev.customerservice.repositories.CustomerRepository;
import com.fouadev.customerservice.services.CustomerService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private StreamBridge streamBridge;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, StreamBridge streamBridge) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.streamBridge = streamBridge;
    }
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        if(customer == null)
            customer = customerMapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer = customerRepository.save(customer);
        CustomerEvent pageEvent = new CustomerEvent(customer.getFirstName(), customer.getEmail(), new Date(), 0L);
        streamBridge.send("customer-topic", pageEvent);

        return customerMapper.fromCustomer(saveCustomer);
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        //customer.setEmail(customerDTO.getEmail());

        Customer updateCustomer = customerRepository.save(customer);


        return customerMapper.fromCustomer(customerRepository.save(updateCustomer));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::fromCustomer).toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}