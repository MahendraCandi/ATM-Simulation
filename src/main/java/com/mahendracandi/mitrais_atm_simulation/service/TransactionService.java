package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    void doTransaction(TransactionType transactionType, Customer customer, BigDecimal amount, LocalDateTime dateTime);

    void doTransaction(Transaction transaction);

    List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int maxSize);

}
