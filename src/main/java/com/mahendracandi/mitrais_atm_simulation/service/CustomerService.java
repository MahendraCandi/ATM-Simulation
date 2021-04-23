package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getCustomers ();

    Optional<Customer> getCustomerByAccountNumber(String accountNumber);

    Optional<Customer> getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber);

    Optional<Customer> updateCustomer(Customer customer);

    Optional<Customer> doLogin(String accountNumber, String pinNumber);
}
