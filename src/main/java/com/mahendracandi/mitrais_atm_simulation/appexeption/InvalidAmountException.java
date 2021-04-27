package com.mahendracandi.mitrais_atm_simulation.appexeption;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(String errorMessage){
        super(errorMessage);
    }
}
