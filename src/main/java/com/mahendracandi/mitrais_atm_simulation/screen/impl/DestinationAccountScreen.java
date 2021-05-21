package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.DestinationAccountValidator;

import java.util.Optional;

public class DestinationAccountScreen extends Screen {
    private Customer destinationCustomer;
    private CustomerService customerService;

    public DestinationAccountScreen(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void showScreen() throws InvalidAccountException {
        MessageUtil.printMessage("Please enter destination account and press enter to continue or\n" +
                "press enter to go back to Transaction: ");
        String destinationAccount = doInput();
        if (destinationAccount.isEmpty()) return;
        DestinationAccountValidator destinationAccountValidator = new DestinationAccountValidator();
        destinationAccountValidator.validate(destinationAccount);

        this.destinationCustomer = customerService.getCustomerByAccountNumber(destinationAccount);
    }

    public Optional<Customer> getDestinationCustomer() {
        return destinationCustomer == null ? Optional.empty() : Optional.of(destinationCustomer);
    }
}
