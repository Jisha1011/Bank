package com.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Type should not be empty")
    private String accountType;
    @NotEmpty(message = "Branch Address should not be empty")
    private String branchAddress;
}
