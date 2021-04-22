package com.mahendracandi.mitrais_atm_simulation.util;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;

import static com.mahendracandi.mitrais_atm_simulation.util.MessageUtil.printInvalidMessage;
import static com.mahendracandi.mitrais_atm_simulation.util.ValidatorUtil.ValidationResult.*;

public class ValidatorUtil {

    private final AppUtil appUtil = new AppUtil();

    public enum ValidationResult {
        ACCOUNT_NUMBER_LENGTH_NOT_VALID("Account Number should have 6 digits"),
        ACCOUNT_NUMBER_NOT_NUMBERS("Account Number should only contains numbers"),
        PIN_LENGTH_NOT_VALID("PIN should have 6 digits"),
        PIN_NOT_NUMBERS("PIN should only contains numbers"),
        LOGIN_INVALID("Invalid Account Number/PIN"),
        INVALID_AMOUNT("Invalid Amount"),
        INVALID_MAXIMUM_AMOUNT("Maximum amount to withdraw is $1000"),
        INVALID_MINIMUM_AMOUNT("Minimum amount to withdraw is $1"),
        INSUFFICIENT_BALANCE("Insufficient balance"),
        INVALID_ACCOUNT("Invalid account"),
        INVALID_REFERENCE_NUMBER("Invalid Reference Number"),
        SUCCESS("Success");

        public final String value;

        ValidationResult(String value) {
            this.value = value;
        }
    }

    public boolean isAccountNumberValid(String accountNumber) {
        if (!appUtil.isLengthValid(accountNumber)) {
            throw new IllegalStateException(ACCOUNT_NUMBER_LENGTH_NOT_VALID.value);
        }
        if (!appUtil.isOnlyNumbers(accountNumber)) {
            throw new IllegalStateException(ACCOUNT_NUMBER_NOT_NUMBERS.value);
        }
        return true;
    }

    public boolean isPinNumberValid(String pinNumber) {
        if (!appUtil.isLengthValid(pinNumber)) {
            throw new IllegalStateException(PIN_LENGTH_NOT_VALID.value);
        }
        if (!appUtil.isOnlyNumbers(pinNumber)) {
            throw new IllegalStateException(PIN_NOT_NUMBERS.value);
        }
        return true;
    }

    public boolean isCustomerLoginValid(Customer customer) {
        if (customer == null) {
            printInvalidMessage(LOGIN_INVALID.value);
            return false;
        }
        return true;
    }

    public boolean isWithdrawAmountValid(String value, Customer customer) {
        boolean result = false;
        boolean isAmountStrContainOnlyNumbers = isAmountStrContainOnlyNumbers(value);
        if (isAmountStrContainOnlyNumbers) {
            int amount = Integer.parseInt(value);
            result = isValueMultipleOfTen(amount) &&
                    isValueNotMoreThanMaximumAmount(amount) &&
                    isCustomerHasSufficientBalance(customer, amount);
        }
        return result;
    }

    public boolean isTransferAmountValid(String value, Customer customer) {
        boolean result = false;
        boolean isAmountStrContainOnlyNumbers = isAmountStrContainOnlyNumbers(value);
        if (isAmountStrContainOnlyNumbers) {
            int amount = Integer.parseInt(value);
            result = isValueMultipleOfTen(amount) &&
                    isValueNotMoreThanMaximumAmount(amount) &&
                    isValueNotLessThanMinimumAmount(amount) &&
                    isCustomerHasSufficientBalance(customer, amount);
        }
        return result;
    }

    public boolean isAmountStrContainOnlyNumbers(String amount) {
        if(!appUtil.isOnlyNumbers(amount)) {
            printInvalidMessage(INVALID_AMOUNT.value);
            return false;
        }
        return true;
    }

    public boolean isValueMultipleOfTen(int amount) {
        if (!appUtil.isValueMultipleOfTen(amount)) {
            printInvalidMessage(INVALID_AMOUNT.value);
            return false;
        }
        return true;
    }

    public boolean isValueNotMoreThanMaximumAmount(int amount) {
        if(!appUtil.isValueMoreThanMaximumAmount(amount)) {
            return true;
        }
        printInvalidMessage(INVALID_MAXIMUM_AMOUNT.value);
        return false;
    }

    public boolean isValueNotLessThanMinimumAmount(int amount) {
        if(!appUtil.isValueLessThanMinimumAmount(amount)) {
            return true;
        }
        printInvalidMessage(INVALID_MINIMUM_AMOUNT.value);
        return false;
    }

    public boolean isCustomerHasSufficientBalance(Customer customer, int amount) {
        if (customer.getBalance().intValue() < amount) {
            printInvalidMessage(INSUFFICIENT_BALANCE.value, amount);
            return false;
        }
        return true;
    }

    public boolean isDestinationAccountValid(String destinationAccount) {
        boolean isDestinationAccountContainOnlyNumbers = isDestinationAccountContainOnlyNumbers(destinationAccount);
        if (isDestinationAccountContainOnlyNumbers) {
            CustomerService customerService = new CustomerServiceImpl();
            Customer customer;

            try {
                customer = customerService.getCustomerByAccountNumber(destinationAccount);
            } catch (Exception e) {
                customer = null;
            }

            boolean isDestinationAccountFound = isDestinationAccountFound(customer);
            if (isDestinationAccountFound) return true;

        }
        return false;
    }

    public boolean isDestinationAccountContainOnlyNumbers(String account) {
        if(appUtil.isOnlyNumbers(account)){
            return true;
        }
        printInvalidMessage(INVALID_ACCOUNT.value);
        return false;
    }

    public boolean isDestinationAccountFound(Customer customer) {
        if (customer == null) {
            printInvalidMessage(INVALID_ACCOUNT.value);
            return false;
        }
        return true;
    }

    public boolean isReferenceNumberValid(String referenceNumber) {
        if (!referenceNumber.isEmpty() && appUtil.isOnlyNumbers(referenceNumber)) {
            return true;
        }
        printInvalidMessage(INVALID_REFERENCE_NUMBER.value);
        return false;
    }






}
