package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;

public class WithdrawScreen extends Screen {

    private BigDecimal amount;
    private Customer customer;

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
        String input = doInput();
        BigDecimal amount = null;
        switch (input) {
            case "1":
                amount = new BigDecimal("10");
                break;
            case "2":
                amount = new BigDecimal("50");
                break;
            case "3":
                amount = new BigDecimal("100");
                break;
            case "4":
                OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen(customer);
                otherWithdrawScreen.showScreen();
                otherWithdrawScreen.readInput();
                if(!otherWithdrawScreen.isExistScreen()) {
                    amount = otherWithdrawScreen.getAmount();
                }
                break;
            case "5":
                this.existScreen = true;
                break;
            default:
                showScreen();
                readInput();
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
