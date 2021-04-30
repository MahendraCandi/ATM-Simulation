package com.mahendracandi.mitrais_atm_simulation.exeption;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(String errorMessage) {
        super(errorMessage);
    }
}
