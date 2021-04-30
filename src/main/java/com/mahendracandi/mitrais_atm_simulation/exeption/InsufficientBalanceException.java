package com.mahendracandi.mitrais_atm_simulation.exeption;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
