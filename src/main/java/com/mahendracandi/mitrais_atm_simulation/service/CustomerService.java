package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers ();

    Customer getCustomerByAccountNumber(String accountNumber);

    Customer updateCustomer(Customer customer);
}
