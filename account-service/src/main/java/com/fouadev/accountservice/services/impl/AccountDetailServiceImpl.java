package com.fouadev.accountservice.services.impl;
/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/

import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.services.AccountDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountDetailServiceImpl implements AccountDetailService {
    @Override
    public AccountDetailDTO addAccountDetail(AccountDetailDTO accountDetailDTO) {
        return null;
    }

    @Override
    public AccountDetailDTO getAccountDetail(String id) {
        return null;
    }

    @Override
    public AccountDetailDTO updateAccountDetail(String id, AccountDetailDTO accountDetailDTO) {
        return null;
    }

    @Override
    public List<AccountDetailDTO> getAllAccountDetails() {
        return null;
    }

    @Override
    public void deleteAccountDetail(String id) {

    }
}