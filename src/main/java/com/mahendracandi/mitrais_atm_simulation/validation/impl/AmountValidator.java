package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.exeption.InvalidAmountException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import java.math.BigDecimal;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.*;

public class AmountValidator implements AppValidator<BigDecimal> {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public void validate(BigDecimal amount) throws InvalidAmountException{
        if (!appUtil.isValueMultipleOfTen(amount.intValue())) {
            throw new InvalidAmountException(INVALID_AMOUNT.value);
        }
        if (appUtil.isValueMoreThanMaximumAmount(amount.intValue())) {
            throw new InvalidAmountException(INVALID_MAXIMUM_AMOUNT.value);
        }
        if (appUtil.isValueLessThanMinimumAmount(amount.intValue())) {
            throw new InvalidAmountException(INVALID_MINIMUM_AMOUNT.value);
        }
    }
}
