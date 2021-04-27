package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
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

        this.doTransaction(transaction);
    }

    @Override
    public void doTransaction(Transaction transaction) {
        Customer customer = transaction.getCustomer();
        BigDecimal deductedBalance = deductedBalance(customer.getBalance(), transaction.getAmount());
        customer.setBalance(deductedBalance);
        customerService.updateCustomer(customer);

        if (transaction.getTransactionType().equals(TransactionType.FUND_TRANSFER)) {
            Customer destinationAccount = transaction.getDestinationAccount();
            destinationAccount.setBalance(addBalance(destinationAccount.getBalance(), transaction.getAmount()));
            customerService.updateCustomer(destinationAccount);
        }
    }

    private BigDecimal deductedBalance(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }

    private BigDecimal addBalance(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount);
    }
}
