package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.transaction.AmountValidatorImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FundTransferConfirmationScreen extends Screen {
    private final Customer customer;
    private final Customer destinationAccount;
    private final BigDecimal transferAmount;
    private final String referenceNumber;

    public FundTransferConfirmationScreen(Customer customer, Customer destinationAccount, BigDecimal transferAmount, String referenceNumber) {
        this.customer = customer;
        this.destinationAccount = destinationAccount;
        this.transferAmount = transferAmount;
        this.referenceNumber = referenceNumber;
    }

    @Override
    public void showScreen() {
        boolean exitLoop = false;
        do {
            MessageUtil.printMessage("Transfer Confirmation" +
                    "\nDestination Account: " + destinationAccount.getAccountNumber() +
                    "\nTransfer Amount    : $" + transferAmount.intValue() +
                    "\nReference Number   : " + referenceNumber);
            MessageUtil.printMessage("1. Confirm Trx");
            MessageUtil.printMessage("2. Cancel Trx");
            MessageUtil.printMessage("Choose option[2]");
            String option = doInput("2");
            switch (option) {
                case "1":
                    ValidationMessage checkBalance = new AmountValidatorImpl().validateBalance(customer, transferAmount);
                    if (checkBalance.isNotSuccess()) {
                        MessageUtil.printAllErrorMessage(checkBalance.getErrorMessages());
                        return;
                    }

                    Transaction transaction = new Transaction();
                    transaction.setTransactionType(TransactionType.FUND_TRANSFER);
                    transaction.setCustomer(customer);
                    transaction.setDestinationAccount(destinationAccount);
                    transaction.setAmount(transferAmount);
                    transaction.setReferenceNumber(referenceNumber);
                    transaction.setDate(LocalDateTime.now());

                    SummaryScreen summaryScreen = new SummaryScreen(transaction);
                    summaryScreen.showScreen();
                    exitLoop = true;
                    break;
                case "2":
                    exitLoop = true;
                    break;
            }
        } while (!exitLoop);
    }
}
