package com.mahendracandi.mitrais_atm_simulation.appexeption;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
