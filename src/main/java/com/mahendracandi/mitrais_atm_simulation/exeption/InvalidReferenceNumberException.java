package com.mahendracandi.mitrais_atm_simulation.exeption;

public class InvalidReferenceNumberException extends Exception{
    public InvalidReferenceNumberException(String errorMessage) {
        super(errorMessage);
    }
}
