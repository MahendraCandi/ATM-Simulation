package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers ();

    Customer getCustomerByAccountNumber(String accountNumber);

    Customer getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber);

    Customer updateCustomer(Customer customer);

    Customer doLogin(String accountNumber, String pinNumber);


}
