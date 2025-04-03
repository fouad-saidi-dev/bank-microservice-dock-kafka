package com.fouadev.accountservice.services.impl;
/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/

import com.fouadev.accountservice.clients.CustomerRestClient;
import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.entities.AccountDetail;
import com.fouadev.accountservice.event.AccountEvent;
import com.fouadev.accountservice.event.EventType;
import com.fouadev.accountservice.mapper.AccountDetailMapper;
import com.fouadev.accountservice.model.Customer;
import com.fouadev.accountservice.repositories.AccountDetailRepo;
import com.fouadev.accountservice.services.AccountDetailService;
import com.fouadev.accountservice.services.KafkaProducerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountDetailServiceImpl implements AccountDetailService {
    private AccountDetailMapper accountDetailMapper;
    private CustomerRestClient customerRestClient;
    private AccountDetailRepo accountDetailRepo;
    private KafkaProducerService producerService;

    public AccountDetailServiceImpl(AccountDetailMapper accountDetailMapper, CustomerRestClient customerRestClient, AccountDetailRepo accountDetailRepo, KafkaProducerService producerService) {
        this.accountDetailMapper = accountDetailMapper;
        this.customerRestClient = customerRestClient;
        this.accountDetailRepo = accountDetailRepo;
        this.producerService = producerService;
    }
    @Override
    public AccountDetailDTO addAccountDetail(AccountDetailDTO accountDetailDTO) {
        AccountDetail accountDetail = accountDetailRepo.findByAccountNumber(accountDetailDTO.getAccountNumber()).orElse(null);

        if (accountDetail == null)
            accountDetail = accountDetailMapper.toAccountDetail(accountDetailDTO);

        accountDetail.setCustomerId(accountDetailDTO.getCustomerId());
        accountDetail.setAccountNumber(String.valueOf((int)(Math.random()*1_000_000_000_000_000L)));

        AccountDetail saveAccountDetail = accountDetailRepo.save(accountDetail);

        System.out.println("account id ====>> "+saveAccountDetail.getId());

        AccountEvent accountEvent = new AccountEvent(
                saveAccountDetail.getAccountNumber(),
                saveAccountDetail.getAccountType(),
                saveAccountDetail.getBalance(),
                EventType.CREATED
        );

        producerService.sendAccountMessage(accountEvent);

        return accountDetailMapper.toAccountDetailDTO(saveAccountDetail);
    }

    @Override
    public AccountDetailDTO getAccountDetail(String id) {
        AccountDetail accountDetail = accountDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountDetail.setCustomerId(customerRestClient.findCustomerById(accountDetail.getCustomerId()).getId());
        accountDetail.setCustomer(customerRestClient.findCustomerById(accountDetail.getCustomerId()));
        return accountDetailMapper.toAccountDetailDTO(accountDetail);
    }

    @Override
    public AccountDetailDTO updateAccountDetail(String id, AccountDetailDTO accountDetailDTO) {
        AccountDetail accountDetail = accountDetailRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        accountDetail.setBalance(accountDetailDTO.getBalance());
        accountDetail.setAccountType(accountDetailDTO.getAccountType());
        accountDetail.setCustomerId(accountDetailDTO.getCustomerId());

        AccountDetail updateAccountDetail = accountDetailRepo.save(accountDetail);

        AccountEvent accountEvent = new AccountEvent(
                updateAccountDetail.getAccountNumber(),
                updateAccountDetail.getAccountType(),
                updateAccountDetail.getBalance(),
                EventType.UPDATED
        );

        producerService.sendAccountMessage(accountEvent);

        return accountDetailMapper.toAccountDetailDTO(updateAccountDetail);
    }

    @Override
    public List<AccountDetailDTO> getAllAccountDetails() {
        List<AccountDetail> accountDetails = accountDetailRepo.findAll();
        accountDetails.forEach(accountDetail -> {
            accountDetail.setCustomerId(customerRestClient.findCustomerById(accountDetail.getCustomerId()).getId());
            accountDetail.setCustomer(customerRestClient.findCustomerById(accountDetail.getCustomerId()));
            System.out.println("customer email ====>> "+accountDetail.getCustomer().getEmail());
        });

        List<AccountDetailDTO> accountDetailDTOS = accountDetails.stream().map(accountDetailMapper::toAccountDetailDTO).toList();
        return accountDetailDTOS;
    }

    @Override
    public void deleteAccountDetail(String id) {
        accountDetailRepo.deleteById(id);
    }
}