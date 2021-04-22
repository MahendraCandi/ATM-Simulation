package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;

import static com.mahendracandi.mitrais_atm_simulation.util.MessageUtil.printMessage;

public class TransactionScreen extends Screen {

    private Customer customer;

    public TransactionScreen() {
    }

    public TransactionScreen(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void showScreen() {
        printMessage("1. Withdraw");
        printMessage("2. Fund Transfer");
        printMessage("3. Exit");
        printMessage("Please choose option[3]: ");
        this.setDefaultInput("3");
    }

    @Override
    public void readInput() {
        boolean exitLoop = false;
        do {
            showScreen();
            String option = doInput();
            switch (option) {
                case "1":
                    WithdrawScreen withdrawScreen = new WithdrawScreen(customer);
                    withdrawScreen.readInput();
                    if (withdrawScreen.isExistScreen()) continue;
                    break;
                case "2":
                    FundTransferScreen fundTransferScreen = new FundTransferScreen(customer);
                    fundTransferScreen.readInput();
                    break;
                case "3":
                    this.existScreen = true;
                    exitLoop = true;
            }
        } while (!exitLoop);

    }

}
