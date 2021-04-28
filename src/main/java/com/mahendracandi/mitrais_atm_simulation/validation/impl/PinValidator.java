package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.appexeption.InvalidPinException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.PIN_LENGTH_NOT_VALID;
import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.PIN_NOT_NUMBERS;

public class PinValidator implements AppValidator<String> {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public void validate(String value) throws InvalidPinException{
        if (!appUtil.isLengthSixDigits(value)) {
            throw new InvalidPinException(PIN_LENGTH_NOT_VALID.value);
        }
        if (!appUtil.isOnlyNumbers(value)) {
            throw new InvalidPinException(PIN_NOT_NUMBERS.value);
        }
    }
}
