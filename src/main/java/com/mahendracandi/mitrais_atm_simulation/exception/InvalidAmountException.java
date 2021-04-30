package com.mahendracandi.mitrais_atm_simulation.exception;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String errorMessage){
        super(errorMessage);
    }
}
