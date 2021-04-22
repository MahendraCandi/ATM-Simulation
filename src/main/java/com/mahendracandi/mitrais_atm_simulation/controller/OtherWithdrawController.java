package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class OtherWithdrawController {

    private final AppUtil appUtil = new AppUtil();
    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<BigDecimal> readWithdrawInput(String input) {
        AppResponse<BigDecimal> appResponse = new AppResponse<>();
        BigDecimal amount = null;
        boolean isResultValid = true;
        String message = "";
        try {
            amount = new BigDecimal(input);
            if (!appUtil.isValueMultipleOfTen(amount.intValue())) throw new NumberFormatException();
            if (appUtil.isValueMoreThanMaximumAmount(amount.intValue())) {
                isResultValid = false;
                message += messageUtil.addDelimiter(INVALID_MAXIMUM_AMOUNT.value);
            }
        } catch (NumberFormatException e) {
            isResultValid = false;
            message += messageUtil.addDelimiter(INVALID_AMOUNT.value);
        }

        if (isResultValid) {
            message = SUCCESS.value;
        }

        appResponse.setData(amount);
        appResponse.setMessage(messageUtil.deleteLastDelimiter(message));
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
