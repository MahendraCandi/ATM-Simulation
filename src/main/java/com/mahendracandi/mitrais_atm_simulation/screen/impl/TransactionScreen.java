package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;

import static com.mahendracandi.mitrais_atm_simulation.util.MessageUtil.printMessage;

public class TransactionScreen extends Screen {

    private final Customer customer;
    private final CustomerService customerService;
    private static final String WITHDRAW_MENU = "1";
    private static final String FUND_TRANSFER_MENU = "2";
    private static final String LAST_TRANSACTION_MENU = "3";
    private static final String EXIT_MENU = "4";

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
            printMessage("3. Print Last Transaction");
            printMessage("4. Exit");
            printMessage("Please choose option[4]: ");
            String option = doInput(EXIT_MENU);
            switch (option) {
                case WITHDRAW_MENU:
                    WithdrawScreen withdrawScreen = new WithdrawScreen(customer, customerService);
                    withdrawScreen.showScreen();
                    break;
                case FUND_TRANSFER_MENU:
                    FundTransferScreen fundTransferScreen = new FundTransferScreen(customer, customerService);
                    fundTransferScreen.showScreen();
                    break;
                case LAST_TRANSACTION_MENU:
                    PrintTransactionScreen printTransactionScreen = new PrintTransactionScreen(customer);
                    printTransactionScreen.showScreen();
                    break;
                case EXIT_MENU:
                    exitLoop = true;
            }
        } while (!exitLoop) ;
    }
}
