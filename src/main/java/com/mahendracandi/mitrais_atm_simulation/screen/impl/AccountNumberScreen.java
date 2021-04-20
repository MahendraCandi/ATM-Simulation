package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class AccountNumberScreen extends Screen {

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter account number: ");
    }

    @Override
    protected void validate() {

    }
}
