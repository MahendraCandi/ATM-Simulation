package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;

public interface CustomerValidator {
    ValidationMessage validate(Customer customer);

    ValidationMessage validate(String value);
}
