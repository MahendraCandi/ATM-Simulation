package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;

public class FundTransferScreen extends Screen {

    private final Customer customer;
    private final CustomerService customerService;

    public FundTransferScreen(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;
    }

    @Override
    public void showScreen() {
        try {
            DestinationAccountScreen destinationAccountScreen = new DestinationAccountScreen(customerService);
            destinationAccountScreen.showScreen();
            Customer destinationAccount = destinationAccountScreen.getDestinationCustomer();

            TransferAmountScreen tfAmountScreen = new TransferAmountScreen();
            tfAmountScreen.showScreen();
            BigDecimal transferAmount = tfAmountScreen.getTransferAmount();

            ReferenceNumberScreen referenceNumberScreen = new ReferenceNumberScreen();
            referenceNumberScreen.showScreen();
            String referenceNumber = referenceNumberScreen.getReferenceNumber();

            FundTransferConfirmationScreen confirmationScreen =
                    new FundTransferConfirmationScreen(customer, destinationAccount, transferAmount, referenceNumber, customerService);
            confirmationScreen.showScreen();
        } catch (Exception e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }
}
