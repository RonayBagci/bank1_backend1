package com.ronay.basic.bank.service;


import com.ronay.basic.bank.entity.Account;
import com.ronay.basic.bank.entity.User;
import com.ronay.basic.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    //TODO: Make interface for userService and turn this UserServiceImpl class

    private  final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findAccountByAccountNumber(String accountNumber){

        return Optional.ofNullable(accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(
                () -> new RuntimeException("The account number is not found : " + accountNumber))); //TODO: string formatting for account number ({})

    }

    public String generateAccountNumber(){
        return "ACCT" + UUID.randomUUID().toString().substring(0,8);
    }

    public Account createAccount(User user){

        String accountNumber = generateAccountNumber();

        Account account = new Account();

        account.setAccountNumber(accountNumber);
        account.setAccountBalance(new BigDecimal("1000.0"));
        account.setCreatedDate(LocalDateTime.now());
        account.setUser(user);

        return  accountRepository.save(account);

   }

   public List<Account> findAllAccounts(){
        return accountRepository.findAll();
   }

   public void transfer(String fromAccountNumber, String toAccountNumber,BigDecimal amount){

        Account fromAccount = findAccountByAccountNumber(fromAccountNumber)
                .orElseThrow(()-> new RuntimeException("Sender account not found"));

        Account toAccount = findAccountByAccountNumber(toAccountNumber)
                .orElseThrow(()-> new RuntimeException("Receiver account not found"));

        if(fromAccount.getAccountBalance().compareTo(amount) >= 0) {
            fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(amount));
            toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

        }else{
            System.out.println("Insufficent funds.");
        }
   }


}
