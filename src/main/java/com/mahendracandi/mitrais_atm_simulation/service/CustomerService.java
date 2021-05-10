package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers ();

    Customer getCustomerByAccountNumber(String accountNumber) throws InvalidAccountException;

    Customer getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber) throws InvalidAccountException;

    Customer updateCustomer(Customer customer) throws InvalidAccountException;

    Customer doLogin(String accountNumber, String pinNumber) throws InvalidAccountException;
}
