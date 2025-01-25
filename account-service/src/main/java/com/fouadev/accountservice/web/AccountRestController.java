package com.fouadev.accountservice.web;

import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.services.AccountDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Created by : Fouad SAIDI on 24/01/2025
 @author : Fouad SAIDI
 @date : 24/01/2025
 @project : bank-microservice-kafka
*/
@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    private AccountDetailService accountDetailService;

    public AccountRestController(AccountDetailService accountDetailService) {
        this.accountDetailService = accountDetailService;
    }
    @PostMapping("")
    public AccountDetailDTO addAccountDetail(@RequestBody AccountDetailDTO accountDetailDTO) {
        return accountDetailService.addAccountDetail(accountDetailDTO);
    }
    @GetMapping("/{id}")
    public AccountDetailDTO getAccountDetail(@PathVariable String id) {
        return accountDetailService.getAccountDetail(id);
    }
    @PutMapping("/{id}")
    public AccountDetailDTO updateAccountDetail(@PathVariable String id, AccountDetailDTO accountDetailDTO) {
        return accountDetailService.updateAccountDetail(id, accountDetailDTO);
    }
    @GetMapping("")
    public List<AccountDetailDTO> getAllAccountDetails() {
        return accountDetailService.getAllAccountDetails();
    }
}