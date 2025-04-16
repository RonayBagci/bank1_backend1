package com.ronay.basic.bank.controller;

import com.ronay.basic.bank.dto.TransferRequest;
import com.ronay.basic.bank.entity.Account;
import com.ronay.basic.bank.entity.User;
import com.ronay.basic.bank.service.AccountService;
import com.ronay.basic.bank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService,UserService userService){
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create/{userId}")
    public Account createAccount(@PathVariable Long userId){
        User user = userService.findUserByUserId(userId);
        return accountService.createAccount(user);
    }

    @GetMapping("/{accountNumber}")
    public Optional<Account> getAccountById(@PathVariable String accountNumber){
        return accountService.findAccountByAccountNumber(accountNumber);

    }

    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccounts(){
        return accountService.findAllAccounts();
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request){
        accountService.transfer(request.getFromAccountNumber(),
                request.getToAccountNumber(),
                request.getAmount());

        return ResponseEntity.ok("Transfer successful!");
    }


}
