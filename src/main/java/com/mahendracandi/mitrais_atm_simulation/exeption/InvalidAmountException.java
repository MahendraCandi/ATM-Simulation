package com.mahendracandi.mitrais_atm_simulation.exeption;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String errorMessage){
        super(errorMessage);
    }
}
