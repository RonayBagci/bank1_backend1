package com.ronay.basic.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId",nullable = false,updatable = false)
    private Long accountId;

    @Column(name = "accountNumber",nullable = false,unique = true)
    private String accountNumber;

    private BigDecimal accountBalance;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;


    public Account(){}

    public Account(Long accountId, String accountNumber, BigDecimal accountBalance, String ownerName,
                   LocalDateTime createdDate, User user){
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.createdDate = createdDate;
        this.user = user;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                ", createdDate=" + createdDate +
                ", user=" + (user != null ? user.getUserId() : null) + //
                '}';
    }
}
