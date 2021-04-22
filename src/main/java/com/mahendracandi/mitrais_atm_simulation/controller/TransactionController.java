package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionService;
import com.mahendracandi.mitrais_atm_simulation.service.TransactionServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil;

public class TransactionController {

    private final TransactionService transactionService = new TransactionServiceImpl();
    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<Transaction> doTransaction(Transaction transaction) {
        AppResponse<Transaction> appResponse = new AppResponse<>();
        boolean isResultValid = true;
        String message = "";
        try {
            transactionService.doTransaction(transaction);
        } catch (Exception e) {
            isResultValid = false;
            message += e.getMessage();
        }

        if (isResultValid) {
            message = ValidatorUtil.ValidationResult.SUCCESS.value;
        }

        appResponse.setData(transaction);
        appResponse.setMessage(messageUtil.deleteLastDelimiter(message));
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
