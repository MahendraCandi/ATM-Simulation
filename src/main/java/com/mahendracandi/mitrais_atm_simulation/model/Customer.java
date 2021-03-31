package com.mahendracandi.mitrais_atm_simulation.model;

import java.math.BigDecimal;

public class Customer {
    private String name;
    private String pin;
    private String accountNumber;
    private BigDecimal balance;

    public Customer() {
    }

    public Customer(String name, String pin, String accountNumber, BigDecimal balance) {
        this.name = name;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", pin='" + pin + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
