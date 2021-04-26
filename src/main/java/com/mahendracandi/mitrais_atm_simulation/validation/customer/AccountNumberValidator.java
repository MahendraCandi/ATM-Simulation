package com.mahendracandi.mitrais_atm_simulation.validation.customer;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.CustomerValidator;

import java.util.ArrayList;
import java.util.List;

public class AccountNumberValidator implements CustomerValidator {

    private final AppUtil appUtil = new AppUtil();

    @Override
    public ValidationMessage validate(Customer customer) {
        return validate(customer.getAccountNumber());
    }

    @Override
    public ValidationMessage validate(String value) {
        ValidationMessage validationMessage = new ValidationMessage();
        List<String> errorMessages = new ArrayList<>();
        if (!appUtil.isLengthSixDigits(value)) {
            validationMessage.setNotSuccess(true);
            errorMessages.add(ValidatorUtil.ValidationResult.ACCOUNT_NUMBER_LENGTH_NOT_VALID.value);
        }

        if (!appUtil.isOnlyNumbers(value)) {
            validationMessage.setNotSuccess(true);
            errorMessages.add(ValidatorUtil.ValidationResult.ACCOUNT_NUMBER_NOT_NUMBERS.value);
        }

        validationMessage.setErrorMessages(errorMessages);

        return validationMessage;
    }
}
