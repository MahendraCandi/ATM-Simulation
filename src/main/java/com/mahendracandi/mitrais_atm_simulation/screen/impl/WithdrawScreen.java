package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class WithdrawScreen extends Screen {



    @Override
    public void showScreen() {
        MessageUtil.printMessage("1. $10");
        MessageUtil.printMessage("2. $50");
        MessageUtil.printMessage("3. $100");
        MessageUtil.printMessage("4. Other");
        MessageUtil.printMessage("5. Back");
        MessageUtil.printMessage("Please choose option[5]: ");
        this.setDefaultInput("5");
    }

    public void readInput() {

    }
}
