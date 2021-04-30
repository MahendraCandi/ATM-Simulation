package com.mahendracandi.mitrais_atm_simulation.exeption;

public class InvalidPinException extends Exception{
    public InvalidPinException(String errorMessage) {
        super(errorMessage);
    }
}
