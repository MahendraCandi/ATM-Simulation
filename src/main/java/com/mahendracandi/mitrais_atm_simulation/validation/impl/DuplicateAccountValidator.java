package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult;
import com.mahendracandi.mitrais_atm_simulation.exception.DuplicateAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateAccountValidator implements AppValidator<List<Customer>> {
    @Override
    public void validate(List<Customer> customers) throws Exception {
        List<String> accountNumbers = customers.stream().map(Customer::getAccountNumber).collect(Collectors.toList());
        List<String> duplicateItems = new AppUtil().getDuplicateItemOnList(accountNumbers);
        if(duplicateItems.size() > 0) {
            String errorMessage = ValidationResult.DUPLICATE_ACCOUNT.value + " " + duplicateItems;
            throw new DuplicateAccountException(errorMessage);
        }
    }
}
