package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;

import static com.mahendracandi.mitrais_atm_simulation.util.MessageUtil.printMessage;

public class TransactionScreen extends Screen {

    private final Customer customer;
    private final CustomerService customerService;

    public TransactionScreen(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;
    }

    @Override
    public void showScreen() {
        boolean exitLoop = false;
        do {
            printMessage("1. Withdraw");
            printMessage("2. Fund Transfer");
            printMessage("3. Exit");
            printMessage("Please choose option[3]: ");
            String option = doInput("3");
            switch (option) {
                case "1":
                    WithdrawScreen withdrawScreen = new WithdrawScreen(customer);
                    withdrawScreen.showScreen();
                    break;
                case "2":
                    FundTransferScreen fundTransferScreen = new FundTransferScreen(customer, customerService);
                    fundTransferScreen.showScreen();
                    break;
                case "3":
                    exitLoop = true;
            }
        } while (!exitLoop) ;
    }
}
