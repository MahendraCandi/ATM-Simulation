package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

public class AccountNumberScreen extends Screen {

    private final ValidatorUtil validatorUtil = new ValidatorUtil();

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter account number: ");
    }

}
