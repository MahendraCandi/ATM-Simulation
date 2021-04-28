package com.mahendracandi.mitrais_atm_simulation.validation;

public interface AppValidator<T> {
    void validate(T t) throws Exception;
}
