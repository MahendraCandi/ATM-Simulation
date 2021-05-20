package com.mahendracandi.mitrais_atm_simulation.validation.impl;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.repository.CustomersByCSV;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class DuplicateRecordValidatorTest {

    @Test
    public void CustomersHasNotDuplicateRecord() {
        String fileResourcePath = new AppUtil().getAbsolutePathFromResourcesDirectory("accounts.csv");
        try {
            CustomersByCSV customerRepos = new CustomersByCSV(fileResourcePath);
            AppValidator<List<String>> appValidator = new DuplicateRecordValidator();
            appValidator.validate(customerRepos.generateCsvRecordAsListOfString(Paths.get(fileResourcePath)));

            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void CustomersHasDuplicateRecord() {
        try {
            String fileResourcePath = new AppUtil().getAbsolutePathFromResourcesDirectory("accounts-duplicate.csv");
            CustomersByCSV customerRepository = new CustomersByCSV(fileResourcePath);
            List<Customer> customers = customerRepository.getAllCustomers();
            AppValidator<List<Customer>> appValidator = new DuplicateAccountValidator();
            appValidator.validate(customers);
        } catch (Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert. fail("Customers didn't have duplicate record");
    }
}