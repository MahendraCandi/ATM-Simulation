package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult;
import com.mahendracandi.mitrais_atm_simulation.exception.DuplicateRecordException;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import java.util.List;

public class DuplicateRecordValidator implements AppValidator<List<String>> {

    @Override
    public void validate(List<String> list) throws DuplicateRecordException {
        List<String> duplicateItems = new AppUtil().getDuplicateItemOnList(list);
        if (duplicateItems.size() > 0) {
            String errorMessage = ValidationResult.DUPLICATE_RECORD.value + " " + duplicateItems;
            throw new DuplicateRecordException(errorMessage);
        }
    }
}
