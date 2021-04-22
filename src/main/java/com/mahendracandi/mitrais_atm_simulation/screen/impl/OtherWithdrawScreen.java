package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.OtherWithdrawController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;

public class OtherWithdrawScreen extends Screen {

    private final OtherWithdrawController otherWithdrawController = new OtherWithdrawController();
    private BigDecimal amount;
    private boolean isInputValid;

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Other Withdraw");
        MessageUtil.printMessage("Enter amount to withdraw: ");
        String input = doInput();

        AppResponse<BigDecimal> appResponse = otherWithdrawController.readWithdrawInput(input);
        if (appResponse.getStatus()) {
            isInputValid = true;
            amount = appResponse.getData();
        } else {
            MessageUtil.printAllErrorMessage(appResponse.getMessage());
        }
    }

    @Override
    protected void readInput() {

    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public boolean isInputValid() {
        return isInputValid;
    }
}
