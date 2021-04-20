package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class ReferenceNumberScreen extends Screen {

    private final String referenceNumber;

    public ReferenceNumberScreen(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Reference Number: (" + referenceNumber + ") ");
        MessageUtil.printMessage("Press enter to continued ");
        setDefaultInput(referenceNumber);
    }

    @Override
    protected void readInput() {

    }
}
