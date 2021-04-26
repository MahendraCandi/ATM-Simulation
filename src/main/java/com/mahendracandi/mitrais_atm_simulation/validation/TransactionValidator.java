package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;

import java.math.BigDecimal;

public interface TransactionValidator {
    ValidationMessage validate(BigDecimal amount);

}
