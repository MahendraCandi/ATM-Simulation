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
        AccountNumberScreen accountNumberScreen = new AccountNumberScreen();
        accountNumberScreen.showScreen();
        String accountNumber = accountNumberScreen.doInput();
        boolean isAccountNumberValid = validatorUtil.isAccountNumberValid(accountNumber);
        if (!isAccountNumberValid) { // guard clause
            return;
        }
        PinNumberScreen pinNumberScreen = new PinNumberScreen();
        pinNumberScreen.showScreen();
        String pinNumber = pinNumberScreen.doInput();
        boolean isPinNumberValid = validatorUtil.isPinNumberValid(pinNumber);
        if (!isPinNumberValid) {
            return;
        }
        Customer customer = customerService.doLogin(accountNumber, pinNumber);
        boolean isCustomerLoginValid = validatorUtil.isCustomerLoginValid(customer);
        if (isCustomerLoginValid) {
            this.customer = customer;
        }

    }

    @Override
    protected void validate() {

    }

    public Customer getCustomer() {
        return customer;
    }

}
