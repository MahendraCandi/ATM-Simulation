package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INVALID_ACCOUNT;

public class DestinationAccountValidator implements AppValidator<String> {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public void validate(String value) throws InvalidAccountException {
        if (!appUtil.isLengthSixDigits(value) && !appUtil.isOnlyNumbers(value)) {
            throw new InvalidAccountException(INVALID_ACCOUNT.value);
        }
    }
}
