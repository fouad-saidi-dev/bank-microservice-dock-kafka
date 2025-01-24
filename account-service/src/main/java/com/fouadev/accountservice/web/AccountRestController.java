package com.fouadev.accountservice.web;

import com.fouadev.accountservice.services.AccountDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}