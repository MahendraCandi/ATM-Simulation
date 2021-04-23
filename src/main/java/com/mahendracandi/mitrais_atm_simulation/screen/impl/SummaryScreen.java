package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.TransactionController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class SummaryScreen extends Screen {

    private final Transaction transaction;
    private final TransactionController transactionController = new TransactionController();

    public SummaryScreen(Transaction transaction) {
        this.transaction = transaction;
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
                    AppResponse<Transaction> appResponse = transactionController.doTransaction(transaction);
                    if (!appResponse.getStatus()) {
                        MessageUtil.printAllErrorMessage(appResponse.getMessage());
                    }
                    exitLoop = true;
                    break;
                case "2":
                    exitLoop = true;
                    break;
            }
        } while (!exitLoop);
    }

    @Override
    protected void readInput() {

    }
}
