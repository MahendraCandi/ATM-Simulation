package com.mahendracandi.mitrais_atm_simulation.validation.customer;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AccountNumberValidator;

import java.util.ArrayList;
import java.util.List;

public class DestinationAccountValidator implements AccountNumberValidator {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public ValidationMessage validateNumber(String value) {
        ValidationMessage validationMessage = new ValidationMessage();
        List<String> errorMessages = new ArrayList<>();
        if (!appUtil.isLengthSixDigits(value) && !appUtil.isOnlyNumbers(value)) {
            validationMessage.setNotSuccess(true);
            errorMessages.add(ValidatorUtil.ValidationResult.INVALID_ACCOUNT.value);
        }

        validationMessage.setErrorMessages(errorMessages);

        return validationMessage;
    }
}
