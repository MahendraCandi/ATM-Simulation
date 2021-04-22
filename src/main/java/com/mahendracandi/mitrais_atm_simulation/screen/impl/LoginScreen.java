package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

public class LoginScreen extends Screen {

    private Customer customer;
    private final CustomerService customerService = new CustomerServiceImpl();
    private final ValidatorUtil validatorUtil = new ValidatorUtil();

    @Override
    public void showScreen() {

    }

    @Override
    public void readInput() {
        AccountNumberScreen accountNumberScreen = new AccountNumberScreen();
        accountNumberScreen.showScreen();
        accountNumberScreen.readInput();
        if (accountNumberScreen.isExistScreen()) return;
        String accountNumber = accountNumberScreen.getInput();

        PinNumberScreen pinNumberScreen = new PinNumberScreen();
        pinNumberScreen.showScreen();
        pinNumberScreen.readInput();
        if (pinNumberScreen.isExistScreen()) return;
        String pinNumber = pinNumberScreen.getInput();

        Customer customer = customerService.doLogin(accountNumber, pinNumber);

        boolean isCustomerLoginValid = validatorUtil.isCustomerLoginValid(customer);
        if (isCustomerLoginValid) {
            this.customer = customer;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

}
