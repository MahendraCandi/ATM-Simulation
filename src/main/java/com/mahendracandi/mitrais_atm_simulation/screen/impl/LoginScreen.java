package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appexeption.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.appexeption.InvalidPinException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.AccountNumberValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.PinValidator;

import java.util.Optional;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class LoginScreen extends Screen {

    private Customer customer;
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void showScreen() {
        try {
            MessageUtil.printMessage("Enter account number: ");
            String accountNumber = doInput();
            AccountNumberValidator accountNumberValidator = new AccountNumberValidator();
            accountNumberValidator.validate(accountNumber);

            MessageUtil.printMessage("Enter PIN: ");
            String pinNumber = doInput();
            PinValidator pinValidator = new PinValidator();
            pinValidator.validate(pinNumber);

            Optional<Customer> opt = customerService.doLogin(accountNumber, pinNumber);
            if (opt.isPresent()) {
                customer = opt.get();
            } else {
                throw new InvalidAccountException(LOGIN_INVALID.value);
            }
        } catch (InvalidAccountException | InvalidPinException e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }

    public Optional<Customer> getCustomer() {
        return customer == null ? Optional.empty() : Optional.of(customer);
    }
}
