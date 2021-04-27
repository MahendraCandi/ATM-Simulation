package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.customer.AccountNumberValidatorImpl;
import com.mahendracandi.mitrais_atm_simulation.validation.customer.PinValidator;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.INVALID_ACCOUNT;
import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.SUCCESS;

public class LoginController {

    private final MessageUtil messageUtil = new MessageUtil();
    private final CustomerService customerService = new CustomerServiceImpl();

    public AppResponse<Customer> doLogin(String accountNumber, String pinNumber) {
        AppResponse<Customer> appResponse = new AppResponse<>();
        Customer customer = null;
        String message = "";
        boolean isResultValid = false;

        ValidationMessage accountNumberValidationMessage = new AccountNumberValidatorImpl().validateNumber(accountNumber);
        ValidationMessage pinValidationMessage = new PinValidator().validate(pinNumber);

        if (!accountNumberValidationMessage.isNotSuccess() && !pinValidationMessage.isNotSuccess()) {
            try {
                customer = customerService.doLogin(accountNumber, pinNumber)
                        .orElseThrow(() -> new IllegalStateException(INVALID_ACCOUNT.value));
                isResultValid = true;
                message = SUCCESS.value;
            } catch (Exception e) {
                message += messageUtil.addDelimiter(e.getMessage());
            }
        } else {
            message += messageUtil.joinMessages(accountNumberValidationMessage.getErrorMessages());
            message += messageUtil.joinMessages(pinValidationMessage.getErrorMessages());
        }

        appResponse.setData(customer);
        appResponse.setMessage(message);
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
