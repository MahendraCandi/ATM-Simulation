package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

public class AccountNumberScreen extends Screen {

    private String accountNumber;

    private final ValidatorUtil validatorUtil = new ValidatorUtil();

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter account number: ");
    }

    @Override
    protected void readInput() {
        String accountNumber = doInput();
        boolean isAccountNumberValid = validatorUtil.isAccountNumberValid(accountNumber);
        if (!isAccountNumberValid) { // guard clause
            return;
        }
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}
