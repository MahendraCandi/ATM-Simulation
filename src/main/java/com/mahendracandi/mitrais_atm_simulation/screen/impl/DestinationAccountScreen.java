package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class DestinationAccountScreen extends Screen {
    @Override
    public void showScreen() {
        MessageUtil.printMessage("Please enter destination account and press enter to continue or");
        MessageUtil.printMessage("press enter to go back to Transaction: ");
    }

}
