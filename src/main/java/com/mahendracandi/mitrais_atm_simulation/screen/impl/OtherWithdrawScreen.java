package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

import java.math.BigDecimal;

public class OtherWithdrawScreen extends Screen {

    private BigDecimal amount;
    private Customer customer;

    public OtherWithdrawScreen() {
    }

    public OtherWithdrawScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Other Withdraw");
        MessageUtil.printMessage("Enter amount to withdraw: ");
    }

    @Override
    protected void readInput() {
        String input = doInput();
        ValidatorUtil validatorUtil = new ValidatorUtil();
        boolean isWithdrawAmountValid = validatorUtil.isWithdrawAmountValid(input, customer);
        if (!isWithdrawAmountValid) {
            this.existScreen = true;
            return;
        }
        this.amount = new BigDecimal(input);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
