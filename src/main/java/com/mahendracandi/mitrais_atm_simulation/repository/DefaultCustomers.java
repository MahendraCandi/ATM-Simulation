package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DefaultCustomers implements CustomerRepository{

    public static List<Customer> CUSTOMERS_ = new ArrayList<Customer>() {{
        add(new Customer("John Doe", "012108", "112233", new BigDecimal("100")));
        add(new Customer("Jane Doe", "932012", "112244", new BigDecimal("40")));
    }};

    @Override
    public List<Customer> getAllCustomers() {
        return CUSTOMERS_;
    }
}
