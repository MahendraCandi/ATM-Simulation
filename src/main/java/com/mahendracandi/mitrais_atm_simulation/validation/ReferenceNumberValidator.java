package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;

public interface ReferenceNumberValidator{
    ValidationMessage validateNumber(String value);
}
