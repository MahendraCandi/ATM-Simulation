package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.model.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {
    List<TransactionHistory> getAllHistoryByAccountNumber(String accountNumber);

    void saveHistory(Transaction transaction, Customer customer, Customer destinationAccount);
}
