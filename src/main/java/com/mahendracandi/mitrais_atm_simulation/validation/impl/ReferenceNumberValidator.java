package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.appexeption.InvalidReferenceNumberException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INVALID_REFERENCE_NUMBER;

public class ReferenceNumberValidator implements AppValidator<String> {

    private final AppUtil appUtil = new AppUtil();

    @Override
    public void validate(String value) throws InvalidReferenceNumberException {
        if (!appUtil.isLengthSixDigits(value) && !appUtil.isOnlyNumbers(value)) {
            throw new InvalidReferenceNumberException(INVALID_REFERENCE_NUMBER.value);
        }
    }
}
