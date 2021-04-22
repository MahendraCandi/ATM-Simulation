package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FundTransferConfirmationScreen extends Screen {

    private FundTransfer fundTransfer;
    private Customer customer;
    private final ValidatorUtil validatorUtil = new ValidatorUtil();
    private final CustomerService customerService = new CustomerServiceImpl();

    public FundTransferConfirmationScreen() {
    }

    public FundTransferConfirmationScreen(FundTransfer fundTransfer) {
        this.fundTransfer = fundTransfer;
    }

    public FundTransferConfirmationScreen(FundTransfer fundTransfer, Customer customer) {
        this.fundTransfer = fundTransfer;
        this.customer = customer;
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
    protected void readInput() {
        boolean exitLoop = false;
        do {
            showScreen();
            String option = doInput();
            switch (option) {
                case "1":
                    String destinationAccount = fundTransfer.getDestinationAccount();
                    boolean isDestinationAccountValid = validatorUtil.isDestinationAccountValid(destinationAccount);
                    boolean isTransferAmountValid = validatorUtil.isTransferAmountValid(
                            fundTransfer.getTransferAmount(), customer);
                    boolean isReferenceNumberValid = validatorUtil.isReferenceNumberValid(
                            fundTransfer.getReferenceNumber());

                    if (isDestinationAccountValid && isTransferAmountValid && isReferenceNumberValid) {
                        Customer destinationCustomer = customerService.getCustomerByAccountNumber(destinationAccount);
                        TransactionType transactionType = TransactionType.FUND_TRANSFER;

                        Transaction transaction = new Transaction();
                        transaction.setTransactionType(transactionType);
                        transaction.setCustomer(customer);
                        transaction.setAmount(new BigDecimal(fundTransfer.getTransferAmount()));
                        transaction.setDate(LocalDateTime.now());
                        transaction.setDestinationAccount(destinationCustomer);
                        transaction.setReferenceNumber(fundTransfer.getReferenceNumber());

                        SummaryScreen summaryScreen = new SummaryScreen(transaction, transactionType);
                        summaryScreen.readInput();
                    }
                    exitLoop = true;
                    break;
                case "2":
                    exitLoop = true;
                    break;
            }
        }while (!exitLoop);

    }
}
