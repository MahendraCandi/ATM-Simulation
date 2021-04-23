package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.FundTransferController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class FundTransferConfirmationScreen extends Screen {

    private final FundTransfer fundTransfer;
    private final Customer customer;
    private final FundTransferController fundTransferController = new FundTransferController();

    public FundTransferConfirmationScreen(FundTransfer fundTransfer, Customer customer) {
        this.fundTransfer = fundTransfer;
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        boolean exitLoop = false;
        do {
            MessageUtil.printMessage(fundTransfer.toConfirmationString());
            MessageUtil.printMessage("1. Confirm Trx");
            MessageUtil.printMessage("2. Cancel Trx");
            MessageUtil.printMessage("Choose option[2]");
            String option = doInput("2");
            switch (option) {
                case "1":
                    AppResponse<Transaction> appResponse = fundTransferController.readFundTransferAmount(fundTransfer, customer);
                    if (appResponse.getStatus()) {
                        SummaryScreen summaryScreen = new SummaryScreen(appResponse.getData());
                        summaryScreen.showScreen();
                    } else {
                        MessageUtil.printAllErrorMessage(appResponse.getMessage());
                    }
                    exitLoop = true;
                    break;
                case "2":
                    exitLoop = true;
                    break;
            }
        } while (!exitLoop);
    }

}
