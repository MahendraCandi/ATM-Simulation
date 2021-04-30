package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.exeption.InvalidAmountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import java.math.BigDecimal;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INSUFFICIENT_BALANCE;

public class CustomerBalanceValidator implements AppValidator<BigDecimal> {

    private final Customer customer;

    public CustomerBalanceValidator(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void validate(BigDecimal amount) throws InvalidAmountException {
        if (customer.getBalance().compareTo(amount) < 0) {
            throw new InvalidAmountException(INSUFFICIENT_BALANCE.value + " $" + amount.intValue());
        }
    }
}
