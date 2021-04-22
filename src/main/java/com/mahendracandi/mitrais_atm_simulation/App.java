package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.LoginScreen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class App {

    public static void main(String[] args) {
        boolean exit = false;
        do {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.showScreen();
            Customer customer = loginScreen.getCustomer();
            System.out.println(customer.toString());

        } while (!exit);
        MessageUtil.printMessage("Bye");
    }
}
