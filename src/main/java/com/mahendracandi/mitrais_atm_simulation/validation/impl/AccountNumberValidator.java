package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.ACCOUNT_NUMBER_LENGTH_NOT_VALID;
import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.ACCOUNT_NUMBER_NOT_NUMBERS;

public class AccountNumberValidator implements AppValidator<String> {

    private final AppUtil appUtil = new AppUtil();

    @Override
    public void validate(String value) throws InvalidAccountException {
        if (!appUtil.isLengthSixDigits(value)) {
            throw new InvalidAccountException(ACCOUNT_NUMBER_LENGTH_NOT_VALID.value);
        }
        if (!appUtil.isOnlyNumbers(value)) {
            throw new InvalidAccountException(ACCOUNT_NUMBER_NOT_NUMBERS.value);
        }
    }
}
