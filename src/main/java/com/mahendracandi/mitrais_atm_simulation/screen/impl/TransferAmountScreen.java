package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class TransferAmountScreen extends Screen {
    @Override
    public void showScreen() {
        MessageUtil.printMessage("Please enter transfer amount and\n" +
                "press enter to continue or\n" +
                "Press enter to go back to Transaction: ");
    }

    @Override
    protected void readInput() {

    }
}
