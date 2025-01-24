package com.fouadev.accountservice.mapper;

import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.entities.AccountDetail;
import com.fouadev.accountservice.repositories.AccountDetailRepo;
import org.springframework.stereotype.Component;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@Component
public class AccountDetailMapper {
    private AccountDetailRepo accountDetailRepo;

    public AccountDetailMapper(AccountDetailRepo accountDetailRepo) {
        this.accountDetailRepo = accountDetailRepo;
    }

    public AccountDetailDTO toAccountDetailDTO(AccountDetail accountDetail) {
        return AccountDetailDTO.builder()
                .id(accountDetail.getId())
                .accountNumber(accountDetail.getAccountNumber())
                .accountType(accountDetail.getAccountType())
                .balance(accountDetail.getBalance())
                .customerId(accountDetail.getCustomerId())
                .build();
    }

    public AccountDetail toAccountDetail(AccountDetailDTO accountDetailDTO) {
        return AccountDetail.builder()
                .id(accountDetailDTO.getId())
                .accountNumber(accountDetailDTO.getAccountNumber())
                .accountType(accountDetailDTO.getAccountType())
                .balance(accountDetailDTO.getBalance())
                .customerId(accountDetailDTO.getCustomerId())
                .build();
    }

}