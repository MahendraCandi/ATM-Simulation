package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;

import static com.mahendracandi.mitrais_atm_simulation.util.MessageUtil.printMessage;

public class TransactionScreen extends Screen {

    @Override
    public void showScreen() {
        printMessage("1. Withdraw");
        printMessage("2. Fund Transfer");
        printMessage("3. Exit");
        printMessage("Please choose option[3]: ");
        this.setDefaultInput("3");
    }

    @Override
    protected void validate() {

    }

}
