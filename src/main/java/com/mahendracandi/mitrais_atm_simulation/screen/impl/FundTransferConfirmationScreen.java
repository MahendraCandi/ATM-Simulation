package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAmountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.CustomerBalanceValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FundTransferConfirmationScreen extends Screen {
    private final Customer customer;
    private final Customer destinationAccount;
    private final BigDecimal transferAmount;
    private final String referenceNumber;
    private final CustomerService customerService;
    private static final String CONFIRM_TRANSACTION = "1";
    private static final String CANCEL_TRANSACTION = "2";


    public FundTransferConfirmationScreen(Customer customer, Customer destinationAccount, BigDecimal transferAmount,
                                          String referenceNumber, CustomerService customerService) {
        this.customer = customer;
        this.destinationAccount = destinationAccount;
        this.transferAmount = transferAmount;
        this.referenceNumber = referenceNumber;
        this.customerService = customerService;
    }

    @Override
    public void showScreen() throws InvalidAmountException {
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
                case CONFIRM_TRANSACTION:
                    validateBalance();

                    Transaction transaction = buildTransaction();

                    SummaryScreen summaryScreen = new SummaryScreen(transaction, customerService);
                    summaryScreen.showScreen();
                    exitLoop = true;
                    break;
                case CANCEL_TRANSACTION:
                    exitLoop = true;
                    break;
            }
        } while (!exitLoop);
    }

    private Transaction buildTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.FUND_TRANSFER);
        transaction.setCustomer(customer);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(transferAmount);
        transaction.setReferenceNumber(referenceNumber);
        transaction.setDate(LocalDateTime.now());
        return transaction;
    }

    private void validateBalance() throws InvalidAmountException {
        CustomerBalanceValidator validator = new CustomerBalanceValidator(customer);
        validator.validate(transferAmount);
    }
}
