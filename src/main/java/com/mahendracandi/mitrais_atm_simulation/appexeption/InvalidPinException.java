package com.mahendracandi.mitrais_atm_simulation.appexeption;

public class InvalidPinException extends Exception{
    public InvalidPinException(String errorMessage) {
        super(errorMessage);
    }
}
