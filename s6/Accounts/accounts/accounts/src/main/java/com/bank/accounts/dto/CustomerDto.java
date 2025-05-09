package com.bank.accounts.dto;

import com.bank.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="Customer",
        description = "Schema to hold customer details"

)
public class CustomerDto {
    @Schema(description = "Name of customer", example = "EasyBytes")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Schema(description =  "Email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
