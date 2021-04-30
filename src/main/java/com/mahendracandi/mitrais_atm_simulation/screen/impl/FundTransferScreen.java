package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;

public class FundTransferScreen extends Screen {

    private final Customer customer;

    public FundTransferScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        try {
            DestinationAccountScreen destinationAccountScreen = new DestinationAccountScreen();
            destinationAccountScreen.showScreen();
            Customer destinationAccount;
            if (destinationAccountScreen.getDestinationCustomer().isPresent()) {
                destinationAccount = destinationAccountScreen.getDestinationCustomer().get();
            } else return;

            TransferAmountScreen tfAmountScreen = new TransferAmountScreen();
            tfAmountScreen.showScreen();
            BigDecimal transferAmount;
            if (tfAmountScreen.getTransferAmount().isPresent()) {
                transferAmount = tfAmountScreen.getTransferAmount().get();
            } else return;

            ReferenceNumberScreen referenceNumberScreen = new ReferenceNumberScreen();
            referenceNumberScreen.showScreen();
            String referenceNumber;
            if (referenceNumberScreen.getReferenceNumber().isPresent()) {
                referenceNumber = referenceNumberScreen.getReferenceNumber().get();
            } else return;

            FundTransferConfirmationScreen confirmationScreen =
                    new FundTransferConfirmationScreen(customer, destinationAccount, transferAmount, referenceNumber);
            confirmationScreen.showScreen();
        } catch (Exception e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }
}
