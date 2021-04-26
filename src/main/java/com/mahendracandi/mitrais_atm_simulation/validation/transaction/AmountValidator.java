package com.mahendracandi.mitrais_atm_simulation.validation.transaction;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.CustomerBalanceValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.TransactionValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class AmountValidator implements TransactionValidator, CustomerBalanceValidator {
    private final AppUtil appUtil = new AppUtil();

    @Override
    public ValidationMessage validate(BigDecimal amount) {
        ValidationMessage validationMessage = new ValidationMessage();
        List<String> errorMessages = new ArrayList<>();

        if (!appUtil.isValueMultipleOfTen(amount.intValue())) {
            validationMessage.setNotSuccess(true);
            errorMessages.add(INVALID_AMOUNT.value);
        } else {
            if (appUtil.isValueMoreThanMaximumAmount(amount.intValue())) {
                validationMessage.setNotSuccess(true);
                errorMessages.add(INVALID_MAXIMUM_AMOUNT.value);
            } else if (appUtil.isValueLessThanMinimumAmount(amount.intValue())) {
                validationMessage.setNotSuccess(true);
                errorMessages.add(INVALID_MINIMUM_AMOUNT.value);
            }
        }

        validationMessage.setErrorMessages(errorMessages);
        return validationMessage;
    }

    @Override
    public ValidationMessage validateBalance(Customer customer, BigDecimal amount) {
        ValidationMessage checkBalance = new ValidationMessage();
        List<String> errorMessages = new ArrayList<>();

        ValidationMessage checkAmount = this.validate(amount);
        if (checkAmount.isNotSuccess()) {
            return checkAmount;
        }

        if (customer.getBalance().compareTo(amount) < 0) {
            checkBalance.setNotSuccess(true);
            errorMessages.add(INSUFFICIENT_BALANCE.value + " $" + amount.intValue());
        }

        checkBalance.setErrorMessages(errorMessages);
        return checkBalance;
    }
}
