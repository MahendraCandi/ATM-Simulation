package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class OtherWithdrawScreen extends Screen {
    @Override
    public void showScreen() {
        MessageUtil.printMessage("Other Withdraw");
        MessageUtil.printMessage("Enter amount to withdraw: ");
    }

    @Override
    protected void validate() {

    }
}
