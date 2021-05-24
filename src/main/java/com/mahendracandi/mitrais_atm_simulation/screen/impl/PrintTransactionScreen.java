package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.TransactionHistory;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionHistoryService;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionHistoryServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.util.List;

public class PrintTransactionScreen extends Screen {

    private final Customer customer;
    private final TransactionHistoryService transactionHistoryService;

    public PrintTransactionScreen(Customer customer) {
        this.customer = customer;
        this.transactionHistoryService = new TransactionHistoryServiceImpl();
    }

    @Override
    public void showScreen() {
        List<TransactionHistory> histories = transactionHistoryService
                .getAllHistoryByAccountNumber(customer.getAccountNumber());
        int size = histories.size();
        if (size == 0) return;
        int fromIndex = size < 10 ? 0 : size - 10;
        List<TransactionHistory> rangeHistories = histories.subList(fromIndex, size);
        int numbering = 1;
        for (TransactionHistory p : rangeHistories) {
            MessageUtil.printMessage(numbering + ". " + p.getRecord() + "\n");
            numbering++;
        }
    }
}
