package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import org.junit.Assert;
import org.junit.Test;

public class CustomersByCSVTest  {

    @Test
    public void generateCsvRecordWithCorrectPath() {
        CustomersByCSV customers = new CustomersByCSV("./data/accounts.csv");
        Assert.assertFalse(customers.getAllCustomers().isEmpty());
    }

    @Test
    public void generateCsvRecordWithIncorrectPath() {
        CustomersByCSV customers = new CustomersByCSV("./incorrect/path/accounts.csv");
        Assert.assertTrue(customers.getAllCustomers().isEmpty());
    }

    @Test
    public void CustomersNotEmpty() {
        String fileFromResources = new AppUtil().getAbsolutePathFromResourcesDirectory("accounts.csv");
        CustomerRepository customerRepository = new CustomersByCSV(fileFromResources);

        Assert.assertFalse(customerRepository.getAllCustomers().isEmpty());
    }
}