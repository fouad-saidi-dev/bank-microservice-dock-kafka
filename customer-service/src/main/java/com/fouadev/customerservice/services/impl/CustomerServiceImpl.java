package com.fouadev.customerservice.services.impl;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.entities.Customer;
import com.fouadev.customerservice.mapper.CustomerMapper;
import com.fouadev.customerservice.repositories.CustomerRepository;
import com.fouadev.customerservice.services.CustomerService;
import com.fouadev.customerservice.services.KafkaProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private KafkaProducerService kafkaProducerService;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, KafkaProducerService kafkaProducerService) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.kafkaProducerService = kafkaProducerService;
    }
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        if(customer == null)
            customer = customerMapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer = customerRepository.save(customer);
        kafkaProducerService.sendMessage("customer-topic","Customer created ===> "+ saveCustomer.getFirstName());
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