package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.WithdrawController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

import java.math.BigDecimal;

public class WithdrawScreen extends Screen {

    private final Customer customer;
    private final ValidatorUtil validatorUtil = new ValidatorUtil();
    private final WithdrawController withdrawController = new WithdrawController();

    public WithdrawScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        BigDecimal amount = null;
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
                    amount = new BigDecimal("10");
                    break;
                case "2":
                    amount = new BigDecimal("50");
                    break;
                case "3":
                    amount = new BigDecimal("100");
                    break;
                case "4":
                    OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
                    otherWithdrawScreen.showScreen();
                    if (otherWithdrawScreen.isInputValid()) amount = otherWithdrawScreen.getAmount();
                    exitLoop = true;
                    break;
                case "5":
                    exitLoop = true;
                    break;
            }

            if (amount != null) {
                AppResponse<Transaction> appResponse = withdrawController.readWithdrawAmount(customer, amount);
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

    private boolean validateAmount(BigDecimal amount) {
        return validatorUtil.isCustomerHasSufficientBalance(customer, amount.intValue());
    }
}
