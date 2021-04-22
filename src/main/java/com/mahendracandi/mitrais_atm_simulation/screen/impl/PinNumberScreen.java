package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

public class PinNumberScreen extends Screen {
    private final ValidatorUtil validatorUtil = new ValidatorUtil();

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter PIN: ");
    }

    @Override
    protected void readInput() {
        String pinNumber = doInput();
        boolean isPinNumberValid = validatorUtil.isPinNumberValid(pinNumber);
        if (isPinNumberValid) {
            this.input = pinNumber;
        }
    }

}
