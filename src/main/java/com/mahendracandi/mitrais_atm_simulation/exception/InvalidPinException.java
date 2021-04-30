package com.mahendracandi.mitrais_atm_simulation.exception;

public class InvalidPinException extends Exception{
    public InvalidPinException(String errorMessage) {
        super(errorMessage);
    }
}
