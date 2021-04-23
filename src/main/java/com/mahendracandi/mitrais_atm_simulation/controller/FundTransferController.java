package com.mahendracandi.mitrais_atm_simulation.controller;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.AppResponse;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.FundTransfer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class FundTransferController {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final AppUtil appUtil = new AppUtil();
    private final MessageUtil messageUtil = new MessageUtil();

    public AppResponse<Transaction> readFundTransferAmount(FundTransfer fundTransfer, Customer customer) {
        AppResponse<Transaction> appResponse = new AppResponse<>();
        Transaction transaction = new Transaction();
        Customer destinationCustomer = new Customer();
        BigDecimal amount = null;
        boolean isResultValid = true;
        String message = "";

        String destinationAccount = fundTransfer.getDestinationAccount();
        String transferAmount = fundTransfer.getTransferAmount();
        String referenceNumber = fundTransfer.getReferenceNumber();

        try {
            if (!appUtil.isOnlyNumbers(destinationAccount)) {
                throw new IllegalStateException();
            }

            destinationCustomer = customerService.getCustomerByAccountNumber(destinationAccount)
                    .orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            isResultValid = false;
            message += messageUtil.addDelimiter(INVALID_ACCOUNT.value);
        }

        try {
            amount = new BigDecimal(transferAmount);
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

        if (!appUtil.isOnlyNumbers(referenceNumber) || !appUtil.isLengthSixDigits(referenceNumber)) {
            isResultValid = false;
            message += messageUtil.addDelimiter(INVALID_REFERENCE_NUMBER.value);
        }

        if (isResultValid) {
            transaction = new Transaction();
            transaction.setTransactionType(TransactionType.FUND_TRANSFER);
            transaction.setCustomer(customer);
            transaction.setAmount(amount);
            transaction.setDestinationAccount(destinationCustomer);
            transaction.setReferenceNumber(referenceNumber);
            transaction.setDate(LocalDateTime.now());

            message = SUCCESS.value;
        }

        appResponse.setData(transaction);
        appResponse.setMessage(messageUtil.deleteLastDelimiter(message));
        appResponse.setStatus(isResultValid);

        return appResponse;
    }
}
