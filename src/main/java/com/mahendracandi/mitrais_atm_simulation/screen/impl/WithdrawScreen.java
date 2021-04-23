package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.WithdrawController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class WithdrawScreen extends Screen {

    private final Customer customer;
    private final WithdrawController withdrawController = new WithdrawController();

    public WithdrawScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        String amountStr = null;
        boolean exitLoop = false;
        do{
            MessageUtil.printMessage("1. $10");
            MessageUtil.printMessage("2. $50");
            MessageUtil.printMessage("3. $100");
            MessageUtil.printMessage("4. Other");
            MessageUtil.printMessage("5. Back");
            MessageUtil.printMessage("Please choose option[5]: ");
            String input = doInput("5");
            switch (input) {
                case "1":
                    amountStr= "10";
                    break;
                case "2":
                    amountStr= "50";
                    break;
                case "3":
                    amountStr= "100";
                    break;
                case "4":
                    MessageUtil.printMessage("Other Withdraw");
                    MessageUtil.printMessage("Enter amount to withdraw: ");
                    amountStr = doInput();
                    break;
                case "5":
                    exitLoop = true;
                    break;
            }

            if (amountStr != null) {
                AppResponse<Transaction> appResponse = withdrawController.readWithdrawAmount(customer, amountStr);
                if (appResponse.getStatus()) {
                    SummaryScreen summaryScreen = new SummaryScreen(appResponse.getData());
                    summaryScreen.showScreen();
                } else {
                    MessageUtil.printAllErrorMessage(appResponse.getMessage());
                }
                exitLoop = true;
            }
        }while (!exitLoop);
    }
}
