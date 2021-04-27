package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.customer.AccountNumberValidatorImpl;
import com.mahendracandi.mitrais_atm_simulation.validation.customer.PinValidator;

import java.util.Optional;

public class LoginScreen extends Screen {

    private Customer customer;
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Enter account number: ");
        String accountNumber = doInput();
        ValidationMessage checkAccount = new AccountNumberValidatorImpl().validateNumber(accountNumber);
        if (checkAccount.isNotSuccess()) {
            MessageUtil.printAllErrorMessage(checkAccount.getErrorMessages());
            return;
        }

        MessageUtil.printMessage("Enter PIN: ");
        String pinNumber = doInput();
        ValidationMessage checkPin = new PinValidator().validate(pinNumber);
        if (checkPin.isNotSuccess()) {
            MessageUtil.printAllErrorMessage(checkPin.getErrorMessages());
            return;
        }

        Optional<Customer> opt = customerService.doLogin(accountNumber, pinNumber);
        if (opt.isPresent()) {
            customer = opt.get();
        } else {
            MessageUtil.printInvalidMessage(ValidatorUtil.ValidationResult.LOGIN_INVALID.value);
        }
    }

    public Optional<Customer> getCustomer() {
        return customer == null ? Optional.empty() : Optional.of(customer);
    }

}
