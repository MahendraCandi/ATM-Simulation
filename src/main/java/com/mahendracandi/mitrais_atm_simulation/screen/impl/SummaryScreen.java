package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class SummaryScreen extends Screen {

    private final Transaction transaction;
    private final TransactionType transactionType;

    public SummaryScreen(Transaction transaction, TransactionType transactionType) {
        this.transaction = transaction;
        this.transactionType = transactionType;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage(transaction.toString(transactionType));
        MessageUtil.printMessage("1. Transaction");
        MessageUtil.printMessage("2. Exit");
        MessageUtil.printMessage("Choose Option[2]");
        this.setDefaultInput("2");
    }
}
