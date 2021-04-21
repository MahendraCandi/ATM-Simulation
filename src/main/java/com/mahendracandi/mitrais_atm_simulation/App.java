package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.*;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionService;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ScreenUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class App {

    private static final CustomerService customerService = new CustomerServiceImpl();

    public static void main(String[] args) {
        App app = new App();

        boolean exit = false;
        do {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.showScreen();
            Customer customer = loginScreen.getCustomer();

            ScreenUtil<String> screenUtil = app.showTransaction(customer);
            if (screenUtil.isBackScreen()) exit = true;

        } while (!exit);
        MessageUtil.printMessage("Bye");
    }

    private ScreenUtil<String> showTransaction(Customer customer) {
        boolean exit = false;
        ScreenUtil<String> screenUtil = new ScreenUtil<>();
        do {
            TransactionScreen transactionScreen = new TransactionScreen();
            transactionScreen.showScreen();
            String option = transactionScreen.doInput();
            switch (option) {
                case "1":
                    ScreenUtil<BigDecimal> withdrawScreen = readWithdrawScreen(customer);
                    if (withdrawScreen.isBackScreen()) continue;

                    ScreenUtil<Transaction> withdrawSummaryScreen = readSummaryScreen(
                            TransactionType.WITHDRAW,
                            customer,
                            withdrawScreen.getPayload(),
                            LocalDateTime.now());
                    if (withdrawSummaryScreen.isBackScreen()) continue;
                    break;
                case "2":
                    ScreenUtil<FundTransfer> fundTransferScreenUtil = readFundTransfer();
                    if (fundTransferScreenUtil.isBackScreen()) continue;

                    FundTransfer fundTransfer = fundTransferScreenUtil.getPayload();
                    ScreenUtil<FundTransfer> transferConfirmationScreenUtil = readFundTransferConfirmationScreen(fundTransfer, customer);
                    if (transferConfirmationScreenUtil.isBackScreen()) continue;

                    fundTransfer = transferConfirmationScreenUtil.getPayload();
                    ScreenUtil<Transaction> fundTransferSummaryScreen = readSummaryScreen(
                            TransactionType.FUND_TRANSFER,
                            customer,
                            new BigDecimal(fundTransfer.getTransferAmount()),
                            LocalDateTime.now(),
                            fundTransfer.getDestinationCustomer(),
                            fundTransfer.getReferenceNumber());
                    if (fundTransferSummaryScreen.isBackScreen()) continue;
                    break;
                case "3":
                    exit = true;
                    screenUtil.setBackScreen(true);
            }
        } while (!exit);
        return screenUtil;
    }

    private ScreenUtil<FundTransfer> readFundTransferConfirmationScreen(FundTransfer fundTransfer, Customer customer) {
        ScreenUtil<FundTransfer> screenUtil;
        boolean exit = false;
        do {
            screenUtil = new ScreenUtil<>();
            FundTransferConfirmationScreen fundTransferConfirmationScreen = new FundTransferConfirmationScreen(fundTransfer);
            fundTransferConfirmationScreen.showScreen();
            String input = fundTransferConfirmationScreen.doInput();

            switch (input) {
                case "1":
                    ScreenUtil<FundTransfer> validateFundTransferScreen = validateFundTransfer(fundTransfer, customer);
                    if (validateFundTransferScreen.isBackScreen()) screenUtil.setBackScreen(true);
                    screenUtil.setPayload(validateFundTransferScreen.getPayload());
                    exit = true;
                    break;
                case "2":
                    screenUtil.setBackScreen(true);
                    exit = true;
                    break;
            }
        } while (!exit);

        return screenUtil;
    }

    private ScreenUtil<FundTransfer> validateFundTransfer(FundTransfer fundTransfer, Customer customer) {
        ScreenUtil<FundTransfer> screenUtil = new ScreenUtil<>();
        String destinationAccount = fundTransfer.getDestinationAccount();

        boolean isDestinationAccountValid;
        boolean isTransferAmountValid;
        boolean isReferenceNumberValid;

        ValidatorUtil validatorUtil = new ValidatorUtil();
        isDestinationAccountValid = validatorUtil.isDestinationAccountValid(destinationAccount);
        isTransferAmountValid = validatorUtil.isTransferAmountValid(fundTransfer.getTransferAmount(), customer);
        isReferenceNumberValid = validatorUtil.isReferenceNumberValid(fundTransfer.getReferenceNumber());

        if (isDestinationAccountValid && isTransferAmountValid && isReferenceNumberValid) {
            Customer destinationCustomer = customerService.getCustomerByAccountNumber(destinationAccount);
            fundTransfer.setDestinationCustomer(destinationCustomer);
            screenUtil.setPayload(fundTransfer);
            return screenUtil;
        }
        screenUtil.setBackScreen(true);
        return screenUtil;
    }

    private ScreenUtil<FundTransfer> readFundTransfer() {
        ScreenUtil<FundTransfer> screenUtil = new ScreenUtil<>();
        FundTransferScreen fundTransferScreen = new FundTransferScreen();
        fundTransferScreen.showScreen();
        FundTransfer fundTransfer = fundTransferScreen.getFundTransfer();
        if (fundTransfer == null) {
            screenUtil.setBackScreen(true);
        } else {
            screenUtil.setPayload(fundTransfer);
        }

        return screenUtil;
    }

    private ScreenUtil<Transaction> readSummaryScreen(
            TransactionType transactionType,
            Customer customer,
            BigDecimal amount,
            LocalDateTime dateTime,
            Customer destinationAccount,
            String referenceNumber) {
        ScreenUtil<Transaction> screenUtil;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(dateTime);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setReferenceNumber(referenceNumber);

        screenUtil = readSummaryScreen(transactionType, transaction);
        return screenUtil;
    }

    private ScreenUtil<Transaction> readSummaryScreen(TransactionType transactionType, Customer customer, BigDecimal amount, LocalDateTime dateTime) {
        ScreenUtil<Transaction> screenUtil;
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(dateTime);

        screenUtil = readSummaryScreen(transactionType, transaction);
        return screenUtil;
    }

    private ScreenUtil<Transaction> readSummaryScreen(TransactionType transactionType, Transaction transaction) {
        ScreenUtil<Transaction> screenUtil = new ScreenUtil<>();
        TransactionService transactionService = new TransactionServiceImpl();
        SummaryScreen summaryScreen = new SummaryScreen(transaction, transactionType);
        summaryScreen.showScreen();
        String input = summaryScreen.doInput();
        switch (input) {
            case "1":
                screenUtil.setPayload(transaction);
                transactionService.doTransaction(transaction);
                break;
            case "2":
                screenUtil.setBackScreen(true);
                break;
            default:
                screenUtil = readSummaryScreen(transactionType, transaction);
        }

        return screenUtil;
    }

    private ScreenUtil<BigDecimal> readWithdrawScreen(Customer customer) {
        ScreenUtil<BigDecimal> screenUtil = new ScreenUtil<>();
        BigDecimal amount = null;
        WithdrawScreen withdrawScreen = new WithdrawScreen();
        withdrawScreen.showScreen();
        String input = withdrawScreen.doInput();
        switch (input) {
            case "1":
                amount = new BigDecimal("10");
                screenUtil.setPayload(amount);
                break;
            case "2":
                amount = new BigDecimal("50");
                screenUtil.setPayload(amount);
                break;
            case "3":
                amount = new BigDecimal("100");
                screenUtil.setPayload(amount);
                break;
            case "4":
                OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen();
                otherWithdrawScreen.showScreen();
                String otherAmount = otherWithdrawScreen.doInput();
                ValidatorUtil validatorUtil = new ValidatorUtil();
                boolean isWithdrawAmountValid = validatorUtil.isWithdrawAmountValid(otherAmount, customer);
                if (isWithdrawAmountValid) {
                    amount = new BigDecimal(otherAmount);
                    screenUtil.setPayload(amount);
                }
                break;
            case "5":
                screenUtil.setBackScreen(true);
                break;
            default:
                screenUtil = readWithdrawScreen(customer);
        }
        return screenUtil;
    }

}
