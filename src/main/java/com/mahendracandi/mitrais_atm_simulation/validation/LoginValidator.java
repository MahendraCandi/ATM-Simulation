package com.mahendracandi.mitrais_atm_simulation.validation;

import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;

import java.util.List;
import java.util.function.Function;

import static com.mahendracandi.mitrais_atm_simulation.validation.LoginValidator.*;
import static com.mahendracandi.mitrais_atm_simulation.validation.LoginValidator.LoginValidationResult.*;

public interface LoginValidator extends Function<String, LoginValidationResult> {
    AppUtil appUtil = new AppUtil();

     static LoginValidator isLengthValid() {
        return value -> appUtil.isLengthSixDigits(value) ? SUCCESS : LENGTH_NOT_VALID;
    }

    static LoginValidator isOnlyNumbers() {
        return value -> appUtil.isOnlyNumbers(value) ? SUCCESS : NOT_ONLY_NUMBERS;
    }

    default LoginValidator and(LoginValidator other) {
        return value -> {
            LoginValidationResult result = this.apply(value);
            return result.equals(SUCCESS) ? other.apply(value) : result;
        };
    }

    enum LoginValidationResult {
        SUCCESS,
        LENGTH_NOT_VALID,
        NOT_ONLY_NUMBERS
    }
}
