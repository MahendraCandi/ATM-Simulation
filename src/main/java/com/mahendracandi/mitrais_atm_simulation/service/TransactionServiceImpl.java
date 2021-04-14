package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService{

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void doTransaction(TransactionType transactionType, Customer customer, BigDecimal amount, LocalDateTime dateTime) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(dateTime);

        BigDecimal deductedBalance = deductedBalance(customer.getBalance(), amount);
        customer.setBalance(deductedBalance);

        customerService.updateCustomer(customer);
    }

    @Override
    public void doTransaction(Transaction transaction) {
        doTransaction(transaction.getTransactionType(), transaction.getCustomer(), transaction.getAmount(), transaction.getDate());
    }

    private BigDecimal deductedBalance(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }
}
