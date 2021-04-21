package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ScreenHelper;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawScreen extends Screen {

    private Customer customer;
    private final ValidatorUtil validatorUtil = new ValidatorUtil();

    public WithdrawScreen() {
    }

    public WithdrawScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage("1. $10");
        MessageUtil.printMessage("2. $50");
        MessageUtil.printMessage("3. $100");
        MessageUtil.printMessage("4. Other");
        MessageUtil.printMessage("5. Back");
        MessageUtil.printMessage("Please choose option[5]: ");
        this.setDefaultInput("5");
    }

    @Override
    public void readInput() {
        boolean exitLoop = false;
        do{
            showScreen();
            String input = doInput();
            ScreenHelper screenHelper = processAmount(input);
            if (screenHelper.isInputNotValid()) {
                continue;
            }

            if (screenHelper.isReturnToTransactionScreen()) {
                // return to transaction screen
                this.existScreen = true;
                break;
            }

            BigDecimal amount = screenHelper.getAmount();
            TransactionType transactionType = TransactionType.WITHDRAW;

            Transaction transaction = new Transaction();
            transaction.setTransactionType(transactionType);
            transaction.setCustomer(customer);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());

            SummaryScreen withdrawSummaryScreen = new SummaryScreen(transaction, transactionType);
            withdrawSummaryScreen.readInput();

            if (withdrawSummaryScreen.isExistScreen()) {
                this.existScreen = true;
            }
            exitLoop = true;
        }while (!exitLoop);
    }

    private ScreenHelper processAmount(String input) {
        ScreenHelper screenHelper = new ScreenHelper();
        BigDecimal amount = null;
        switch (input) {
            case "1":
                amount = new BigDecimal("10");
                if (!validateAmount(amount)) screenHelper.setInputNotValid(true);
                break;
            case "2":
                amount = new BigDecimal("50");
                if (!validateAmount(amount)) screenHelper.setInputNotValid(true);
                break;
            case "3":
                amount = new BigDecimal("100");
                if (!validateAmount(amount)) screenHelper.setInputNotValid(true);
                break;
            case "4":
                OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen(customer);
                otherWithdrawScreen.showScreen();
                otherWithdrawScreen.readInput();
                if(otherWithdrawScreen.isExistScreen()) {
                    screenHelper.setInputNotValid(true);
                } else {
                    amount = otherWithdrawScreen.getAmount();
                }
                break;
            case "5":
                screenHelper.setReturnToTransactionScreen(true);
                break;
            default:
                screenHelper.setInputNotValid(true);
        }
        screenHelper.setAmount(amount);
        return screenHelper;
    }

    private boolean validateAmount(BigDecimal amount) {
        return validatorUtil.isCustomerHasSufficientBalance(customer, amount.intValue());
    }
}
