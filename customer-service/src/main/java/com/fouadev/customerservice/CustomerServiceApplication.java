package com.fouadev.customerservice;

import com.fouadev.customerservice.dto.CustomerDTO;
import com.fouadev.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	//@Bean
	CommandLineRunner init(CustomerService customerService){

		return args -> {
			CustomerDTO customerDTO = CustomerDTO.builder()
					.firstName("Fouad")
					.lastName("Saidi")
					.email("fouadev@gmail.com")
					.build();

			customerService.addCustomer(customerDTO);
		};
	}
}
