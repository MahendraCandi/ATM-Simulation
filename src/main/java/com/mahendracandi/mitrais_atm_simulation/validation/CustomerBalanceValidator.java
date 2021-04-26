package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;

import java.math.BigDecimal;

public interface CustomerBalanceValidator {
    ValidationMessage validateBalance(Customer customer, BigDecimal amount);
}
