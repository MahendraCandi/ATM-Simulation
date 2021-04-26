package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.transaction.AmountValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.INVALID_AMOUNT;
import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.SUCCESS;

public class WithdrawController {

    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<Transaction> readWithdrawAmount(Customer customer, String amountStr) {
        AppResponse<Transaction> appResponse = new AppResponse<>();
        Transaction transaction = null;
        BigDecimal amount = null;
        boolean isResultValid = false;
        String message;

        try {
            amount = new BigDecimal(amountStr);

            ValidationMessage checkBalance = new AmountValidator().validateBalance(customer, amount);
            if (checkBalance.isNotSuccess()) {
                message = messageUtil.joinMessages(checkBalance.getErrorMessages());
            } else {
                isResultValid = true;
                message = SUCCESS.value;
            }
        } catch (NumberFormatException e) {
            message = messageUtil.addDelimiter(INVALID_AMOUNT.value);
        }

        if (isResultValid) {
            transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAW);
            transaction.setCustomer(customer);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
        }

        appResponse.setData(transaction);
        appResponse.setMessage(message);
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
