package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appexeption.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.DestinationAccountValidator;

import java.util.Optional;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.INVALID_ACCOUNT;

public class DestinationAccountScreen extends Screen {
    private Customer destinationCustomer;
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void showScreen() throws InvalidAccountException {
        MessageUtil.printMessage("Please enter destination account and press enter to continue or\n" +
                "press enter to go back to Transaction: ");
        String destinationAccount = doInput();
        if (destinationAccount.isEmpty()) return;
        DestinationAccountValidator destinationAccountValidator = new DestinationAccountValidator();
        destinationAccountValidator.validate(destinationAccount);

        Optional<Customer> optCustomer = customerService.getCustomerByAccountNumber(destinationAccount);
        if (optCustomer.isPresent()) {
            destinationCustomer = optCustomer.get();
        } else throw new InvalidAccountException(INVALID_ACCOUNT.value);
    }

    public Optional<Customer> getDestinationCustomer() {
        return destinationCustomer == null ? Optional.empty() : Optional.of(destinationCustomer);
    }
}
