package com.bank.accounts.controller;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.AccountsContactInfo;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(
        name = "CRUD operations inside account",
        description = "Contains the api and other details"
)
@RestController
@RequestMapping(path="/api", produces ={MediaType.APPLICATION_JSON_VALUE})

@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfo accountsContactInfo;

    public AccountsController(IAccountsService iAccountsService){
        this. iAccountsService = iAccountsService;
    }
    @Operation(
            summary = "Create account API",
            description = "Creates account with customer details"
    )
    @ApiResponses({
            @ApiResponse(
            responseCode = "200",
            description = "Created Successfully"),
    @ApiResponse(
            responseCode = "500",
            description ="Failed to create"
    )}
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

    iAccountsService.createAccount(customerDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits" ) String mobileNumber) {
CustomerDto customerDto = iAccountsService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);}


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build API",
            description = "get the build version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fetched"),
            @ApiResponse(
                    responseCode = "500",
                    description ="Failed to fetch"
            )
    })
@GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {

        return ResponseEntity.status(HttpStatus.OK).body(buildVersion); }


    @Operation(
            summary = "GET JAVA VERSION",
            description = "get the java version"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fetched"),
            @ApiResponse(
                    responseCode = "500",
                    description ="Failed to fetch"
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {

        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME")); }
    @Operation(
            summary = "GET CONTACT INFO",
            description = "get the java version"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fetched"),
            @ApiResponse(
                    responseCode = "500",
                    description ="Failed to fetch"
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfo   > getContactInfo() {

        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfo); }
}


