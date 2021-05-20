package com.mahendracandi.mitrais_atm_simulation.appenum;

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
    DUPLICATE_RECORD("There can't be duplicated records"),
    DUPLICATE_ACCOUNT("There can't be 2 different accounts with the same Account Number");

    public final String value;

    ValidationResult(String value) {
        this.value = value;
    }
}
