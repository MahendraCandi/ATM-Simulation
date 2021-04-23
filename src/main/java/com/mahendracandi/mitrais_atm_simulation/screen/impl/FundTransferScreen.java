package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.FundTransferController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class FundTransferScreen extends Screen {

    private final AppUtil appUtil = new AppUtil();
    private final Customer customer;
    private final FundTransferController fundTransferController = new FundTransferController();

    public FundTransferScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Please enter destination account and press enter to continue or\n" +
                "press enter to go back to Transaction: ");
        String destinationAccount = doInput();
        if (appUtil.isValueNullOrEmpty(destinationAccount)) {
            return;
        }

        MessageUtil.printMessage("Please enter transfer amount and\n" +
                "press enter to continue or\n" +
                "Press enter to go back to Transaction: ");
        String transferAmount = doInput();
        if (appUtil.isValueNullOrEmpty(transferAmount)) {
            return;
        }

        int randomNumber = appUtil.getRandomNumber();
        MessageUtil.printMessage("Reference Number: (" + randomNumber + ")\n" +
                "Press enter to continued ");
        String referenceNumber = doInput(String.valueOf(randomNumber));

        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setDestinationAccount(destinationAccount);
        fundTransfer.setTransferAmount(transferAmount);
        fundTransfer.setReferenceNumber(referenceNumber);

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
