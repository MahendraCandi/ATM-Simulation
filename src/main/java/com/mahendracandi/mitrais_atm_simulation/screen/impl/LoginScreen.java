package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.controller.LoginController;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

public class LoginScreen extends Screen {

    private Customer customer;

    @Override
    public void showScreen() {
        boolean exit = false;
        do {
            MessageUtil.printMessage("Enter account number: ");
            String accountNumber = doInput();
            MessageUtil.printMessage("Enter PIN: ");
            String pinNumber = doInput();

            LoginController loginController = new LoginController();
            AppResponse<Customer> appResponse = loginController.doLogin(accountNumber, pinNumber);
            if (appResponse.getStatus()) {
                exit = true;
                customer = appResponse.getData();
            } else {
                MessageUtil.printAllErrorMessage(appResponse.getMessage());
            }
        } while (!exit);
    }

    public Customer getCustomer() {
        return customer;
    }

}
