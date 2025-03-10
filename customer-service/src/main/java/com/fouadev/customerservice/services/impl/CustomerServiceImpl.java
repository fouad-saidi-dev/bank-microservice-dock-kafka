package com.fouadev.customerservice.services.impl;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.entities.Customer;
import com.fouadev.customerservice.event.CustomerEvent;
import com.fouadev.customerservice.event.EventType;
import com.fouadev.customerservice.mapper.CustomerMapper;
import com.fouadev.customerservice.repositories.CustomerEventRepo;
import com.fouadev.customerservice.repositories.CustomerRepository;
import com.fouadev.customerservice.services.CustomerService;
import com.fouadev.customerservice.services.KafkaProducerService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private StreamBridge streamBridge;
    private CustomerEventRepo customerEventRepo;
    private KafkaProducerService kafkaProducerService;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, StreamBridge streamBridge, CustomerEventRepo customerEventRepo, KafkaProducerService kafkaProducerService) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.streamBridge = streamBridge;
        this.customerEventRepo = customerEventRepo;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByEmail(customerDTO.getEmail());
        if (customer == null)
            customer = customerMapper.fromCustomerDTO(customerDTO);

        Customer saveCustomer = customerRepository.save(customer);

        CustomerEvent pageEvent = CustomerEvent.builder()
                .name(customer.getFirstName() + " " + customer.getLastName())
                .email(customer.getEmail())
                .type(EventType.CREATED)
                .duration(0L)
                .build();

        kafkaProducerService.sendMessage(pageEvent);
        customerEventRepo.save(pageEvent);

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

        CustomerEvent pageEvent = CustomerEvent.builder()
                .name(customer.getFirstName() + " " + customer.getLastName())
                .email(customer.getEmail())
                .type(EventType.UPDATED)
                .duration(0L)
                .build();


        kafkaProducerService.sendMessage(pageEvent);
        customerEventRepo.save(pageEvent);
        return customerMapper.fromCustomer(customerRepository.save(updateCustomer));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::fromCustomer).toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).get();

        CustomerEvent pageEvent = CustomerEvent.builder()
                .name(customer.getFirstName() + " " + customer.getLastName())
                .email(customer.getEmail())
                .type(EventType.DELETED)
                .duration(0L)
                .build();

//        streamBridge.send("customer-topic", pageEvent);

        kafkaProducerService.sendMessage(pageEvent);
        customerEventRepo.save(pageEvent);

        customerRepository.deleteById(id);
    }
}