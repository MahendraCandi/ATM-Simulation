package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.repository.CustomerRepository;
import com.mahendracandi.mitrais_atm_simulation.repository.CustomersByCSV;
import org.junit.Assert;
import org.junit.Test;

public class CustomerServiceImplTest {

    @Test
    public void shouldGetCustomerFromCSVFile() {
        CustomerRepository customerRepository = new CustomersByCSV("./data/accounts.csv");
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        Assert.assertTrue(customerService.getCustomers().size() > 2);
    }

    @Test
    public void shouldGetCustomerFromStaticMemory() {
        CustomerRepository customerRepository = new CustomersByCSV("./incorrect/file/accounts.csv");
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        int testSize = customerService.getCustomers().size();

        Assert.assertTrue(testSize <= 2 && testSize > 0);
    }
}