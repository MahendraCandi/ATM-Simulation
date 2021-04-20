package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class FundTransferConfirmationScreen extends Screen {

    private FundTransfer fundTransfer;

    public FundTransferConfirmationScreen(FundTransfer fundTransfer) {
        this.fundTransfer = fundTransfer;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage(fundTransfer.toConfirmationString());
        MessageUtil.printMessage("1. Confirm Trx");
        MessageUtil.printMessage("2. Cancel Trx");
        MessageUtil.printMessage("Choose option[2]");
        this.setDefaultInput("2");
    }

    @Override
    protected void validate() {

    }
}
