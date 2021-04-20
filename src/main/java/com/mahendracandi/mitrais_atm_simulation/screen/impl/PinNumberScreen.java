package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class PinNumberScreen extends Screen {

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter PIN: ");
    }

    @Override
    protected void validate() {

    }

}
