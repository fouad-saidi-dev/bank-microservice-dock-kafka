package com.fouadev.accountservice;

import com.fouadev.accountservice.clients.CustomerRestClient;
import com.fouadev.accountservice.dto.AccountDetailDTO;
import com.fouadev.accountservice.dto.TransactionDTO;
import com.fouadev.accountservice.entities.AccountDetail;
import com.fouadev.accountservice.enums.AccountType;
import com.fouadev.accountservice.enums.TransactionType;
import com.fouadev.accountservice.mapper.AccountDetailMapper;
import com.fouadev.accountservice.services.AccountDetailService;
import com.fouadev.accountservice.services.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
    //@Bean
	CommandLineRunner init(AccountDetailService accountDetailService,
						   TransactionService transactionService,
						   CustomerRestClient customerRestClient,
						   AccountDetailMapper mapper
	){
		return args -> {

			customerRestClient.findAllCustomers().forEach(customer -> {
				AccountDetailDTO accountDetailDTO = AccountDetailDTO.builder()
						.customerId(customer.getId())
						.accountType(AccountType.SAVINGS)
						.accountNumber(String.valueOf((int)(Math.random()*1_000_000_000_000_000L)))
						.balance(123.0)
						.build();
				AccountDetailDTO savedAccountDetailDTO = accountDetailService.addAccountDetail(accountDetailDTO);
				AccountDetail accountDetail = mapper.toAccountDetail(savedAccountDetailDTO);
				TransactionDTO transactionDTO = TransactionDTO.builder()
						.amount(123.0)
						.transactionType(TransactionType.DEPOSIT)
						.transactionDate(LocalDateTime.now())
						.accountDetail(accountDetail)
						.build();
				transactionService.addTransaction(transactionDTO);

			});
		};
	}
}
