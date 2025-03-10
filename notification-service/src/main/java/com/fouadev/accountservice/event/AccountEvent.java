package com.fouadev.accountservice.event;

import com.fouadev.accountservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEvent {
    private String accountNumber;
    private AccountType accountType;
    private Double balance;
    private EventType type;
}