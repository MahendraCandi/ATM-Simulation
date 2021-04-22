package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;

public class FundTransferScreen extends Screen {

    private final AppUtil appUtil = new AppUtil();
    private FundTransfer fundTransfer;
    private Customer customer;

    public FundTransferScreen() {
    }

    public FundTransferScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
    }

    @Override
    protected void readInput() {
        DestinationAccountScreen destinationAccountScreen = new DestinationAccountScreen();
        destinationAccountScreen.showScreen();
        String destinationAccount = destinationAccountScreen.doInput();
        if (appUtil.isValueNullOrEmpty(destinationAccount)) {
            return;
        }

        TransferAmountScreen transferAmountScreen = new TransferAmountScreen();
        transferAmountScreen.showScreen();
        String transferAmount = transferAmountScreen.doInput();
        if (appUtil.isValueNullOrEmpty(transferAmount)) {
            return;
        }

        int randomNumber = appUtil.getRandomNumber();
        ReferenceNumberScreen referenceNumberScreen = new ReferenceNumberScreen(String.valueOf(randomNumber));
        referenceNumberScreen.showScreen();
        String referenceNumber = referenceNumberScreen.doInput();

        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setDestinationAccount(destinationAccount);
        fundTransfer.setTransferAmount(transferAmount);
        fundTransfer.setReferenceNumber(referenceNumber);

        FundTransferConfirmationScreen fundTransferConfirmationScreen = new FundTransferConfirmationScreen(fundTransfer, customer);
        fundTransferConfirmationScreen.readInput();
    }

    public FundTransfer getFundTransfer() {
        return fundTransfer;
    }

    private void setFundTransfer(FundTransfer fundTransfer) {
        this.fundTransfer = fundTransfer;
    }
}
