package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    private List<Customer> customers = new ArrayList<Customer>() {{
        add(new Customer("John Doe", "012108", "112233", new BigDecimal("100")));
        add(new Customer("Jane Doe", "932012", "112244", new BigDecimal("40")));
    }};

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerByAccountNumber(String accountNumber) {
        return customers.stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    @Override
    public Optional<Customer> getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber) {
        return customers.stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber) && p.getPin().equals(pinNumber))
                .findFirst();
    }

    @Override
    public Optional<Customer> updateCustomer(Customer customer) {
        customers.removeIf(p -> p.getAccountNumber().equals(customer.getAccountNumber()));
        customers.add(customer);
        return getCustomerByAccountNumber(customer.getAccountNumber());
    }

    @Override
    public Optional<Customer> doLogin(String accountNumber, String pinNumber) {
        return getCustomerByAccountAndPinNumber(accountNumber, pinNumber);
    }
}
