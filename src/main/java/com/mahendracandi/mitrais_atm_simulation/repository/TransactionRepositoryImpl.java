package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    private static List<Transaction> TRANSACTIONS = new ArrayList<>();

    @Override
    public List<Transaction> getAllTransactionByAccountNumber(String accountNumber) {
        return TRANSACTIONS;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        TRANSACTIONS.add(transaction);
    }
}
