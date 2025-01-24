package com.fouadev.accountservice.services;

import com.fouadev.accountservice.dto.AccountDetailDTO;

import java.util.List;

public interface AccountDetailService {
    AccountDetailDTO addAccountDetail(AccountDetailDTO accountDetailDTO);
    AccountDetailDTO getAccountDetail(String id);
    AccountDetailDTO updateAccountDetail(String id, AccountDetailDTO accountDetailDTO);
    List<AccountDetailDTO> getAllAccountDetails();
    void deleteAccountDetail(String id);
}
