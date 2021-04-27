package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;

public interface AppValidator<T> {
    void validate(T t) throws Exception;
}
