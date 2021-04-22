package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.INSUFFICIENT_BALANCE;
import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.SUCCESS;

public class WithdrawController {

    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<Transaction> readWithdrawAmount(Customer customer, BigDecimal amount) {
        AppResponse<Transaction> appResponse = new AppResponse<>();
        Transaction transaction = null;
        boolean isResultValid = true;
        String message = "";

        if (customer.getBalance().compareTo(amount) < 0) {
            isResultValid = false;
            message += messageUtil.addDelimiter(INSUFFICIENT_BALANCE.value + " $" + amount);
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
