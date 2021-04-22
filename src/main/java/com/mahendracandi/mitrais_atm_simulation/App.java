package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.LoginScreen;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.TransactionScreen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class App {

    public static void main(String[] args) {
        boolean exit = false;
        do {
            try {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.readInput();
                Customer customer = loginScreen.getCustomer();

                TransactionScreen transactionScreen = new TransactionScreen(customer);
                transactionScreen.readInput();

                if (transactionScreen.isExistScreen()) exit = true;

            } catch (Exception e) {
                MessageUtil.printInvalidMessage(e.getMessage());
            }

        } while (!exit);
        MessageUtil.printMessage("Bye");
    }
}
