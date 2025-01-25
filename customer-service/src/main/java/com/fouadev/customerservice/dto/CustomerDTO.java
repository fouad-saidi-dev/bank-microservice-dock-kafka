package com.fouadev.customerservice.dto;


import lombok.*;


@Getter
@Setter
@Builder
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}