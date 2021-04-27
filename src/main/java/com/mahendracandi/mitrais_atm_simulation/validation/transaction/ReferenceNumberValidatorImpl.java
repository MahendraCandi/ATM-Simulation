package com.mahendracandi.mitrais_atm_simulation.validation.transaction;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.ReferenceNumberValidator;

import java.util.ArrayList;
import java.util.List;

public class ReferenceNumberValidatorImpl implements ReferenceNumberValidator {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public ValidationMessage validateNumber(String value) {
        ValidationMessage validationMessage = new ValidationMessage();
        List<String> errorMessages = new ArrayList<>();
        if (!appUtil.isLengthSixDigits(value) && !appUtil.isOnlyNumbers(value)) {
            validationMessage.setNotSuccess(true);
            errorMessages.add(ValidatorUtil.ValidationResult.INVALID_REFERENCE_NUMBER.value);
        }

        validationMessage.setErrorMessages(errorMessages);

        return validationMessage;
    }
}
