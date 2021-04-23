package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;
import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.INVALID_AMOUNT;

public class WithdrawController {

    private final AppUtil appUtil = new AppUtil();
    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<Transaction> readWithdrawAmount(Customer customer, String amountStr) {
        AppResponse<Transaction> appResponse = new AppResponse<>();
        Transaction transaction = null;
        BigDecimal amount = null;
        boolean isResultValid = true;
        String message = "";

        try {
            amount = new BigDecimal(amountStr);
            if (!appUtil.isValueMultipleOfTen(amount.intValue())) throw new NumberFormatException();

            if (appUtil.isValueMoreThanMaximumAmount(amount.intValue())) {
                isResultValid = false;
                message += messageUtil.addDelimiter(INVALID_MAXIMUM_AMOUNT.value);
            } else if (appUtil.isValueLessThanMinimumAmount(amount.intValue())) {
                isResultValid = false;
                message += messageUtil.addDelimiter(INVALID_MINIMUM_AMOUNT.value);
            } else {
                if (customer.getBalance().compareTo(amount) < 0) {
                    isResultValid = false;
                    message += messageUtil.addDelimiter(INSUFFICIENT_BALANCE.value + " $" + amount);
                }
            }
        } catch (NumberFormatException e) {
            isResultValid = false;
            message += messageUtil.addDelimiter(INVALID_AMOUNT.value);
        }

        if (isResultValid) {
            transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAW);
            transaction.setCustomer(customer);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());

            message = SUCCESS.value;
        }

        appResponse.setData(transaction);
        appResponse.setMessage(messageUtil.deleteLastDelimiter(message));
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
