package com.mahendracandi.mitrais_atm_simulation.appexeption;

public class InvalidReferenceNumberException extends Exception{
    public InvalidReferenceNumberException(String errorMessage) {
        super(errorMessage);
    }
}
