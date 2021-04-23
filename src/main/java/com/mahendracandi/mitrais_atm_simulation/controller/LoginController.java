package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class LoginController {

    private final AppUtil appUtil = new AppUtil();
    private final MessageUtil messageUtil = new MessageUtil();
    private final CustomerService customerService = new CustomerServiceImpl();

    public AppResponse<Customer> doLogin(String accountNumber, String pinNumber) {
        AppResponse<Customer> appResponse = new AppResponse<>();
        Customer customer = null;
        boolean isResultValid = true;
        String message = "";

        if (!appUtil.isLengthSixDigits(accountNumber)) {
            isResultValid = false;
            message += messageUtil.addDelimiter(ACCOUNT_NUMBER_LENGTH_NOT_VALID.value);
        }

        if (!appUtil.isOnlyNumbers(accountNumber)) {
            isResultValid = false;
            message += messageUtil.addDelimiter(ACCOUNT_NUMBER_NOT_NUMBERS.value);
        }

        if (!appUtil.isLengthSixDigits(pinNumber)) {
            isResultValid = false;
            message += messageUtil.addDelimiter(PIN_LENGTH_NOT_VALID.value);
        }

        if (!appUtil.isOnlyNumbers(pinNumber)) {
            isResultValid = false;
            message += messageUtil.addDelimiter(PIN_NOT_NUMBERS.value);
        }

        if (isResultValid) {
            try {
                customer = customerService.doLogin(accountNumber, pinNumber)
                        .orElseThrow(IllegalArgumentException::new);
                message = SUCCESS.value;
            } catch (Exception e) {
                isResultValid = false;
                message += messageUtil.addDelimiter(e.getMessage());
            }
        }

        appResponse.setData(customer);
        appResponse.setMessage(messageUtil.deleteLastDelimiter(message));
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
