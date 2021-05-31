package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> getAllTransactionByAccountNumber(String accountNumber);

    void saveTransaction(Transaction transaction);
}
