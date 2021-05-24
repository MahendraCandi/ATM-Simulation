package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionService;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class SummaryScreen extends Screen {

    private final Transaction transaction;
    private final TransactionService transactionService;

    public SummaryScreen(Transaction transaction, CustomerService customerService) {
        this.transaction = transaction;
        this.transactionService = new TransactionServiceImpl(customerService);
    }

    @Override
    public void showScreen() {
        boolean exitLoop = false;
        do {
            MessageUtil.printMessage(transaction.toSummaryString());
            MessageUtil.printMessage("1. Transaction");
            MessageUtil.printMessage("2. Exit");
            MessageUtil.printMessage("Choose Option[2]");
            String option = doInput("2");
            switch (option) {
                case "1":
                    transactionService.doTransaction(transaction);
                    exitLoop = true;
                    break;
                case "2":
                    exitLoop = true;
                    break;
            }
        } while (!exitLoop);
    }

}
