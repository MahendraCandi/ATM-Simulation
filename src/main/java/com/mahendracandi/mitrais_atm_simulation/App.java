package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.LoginScreen;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.TransactionScreen;

import java.util.Optional;

public class App {

    public static void main(String[] args) {
        do {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.showScreen();
            Optional<Customer> customer = loginScreen.getCustomer();
            if (customer.isPresent()) {
                TransactionScreen transactionScreen = new TransactionScreen(customer.get());
                transactionScreen.showScreen();
            }
        } while (true);
    }
}
