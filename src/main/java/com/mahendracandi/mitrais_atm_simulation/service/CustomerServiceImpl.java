package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INVALID_ACCOUNT;
import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.LOGIN_INVALID;

public class CustomerServiceImpl implements CustomerService{

    private static List<Customer> customers = new ArrayList<Customer>() {{
        add(new Customer("John Doe", "012108", "112233", new BigDecimal("100")));
        add(new Customer("Jane Doe", "932012", "112244", new BigDecimal("40")));
    }};

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerByAccountNumber(String accountNumber) throws InvalidAccountException {
        return customers.stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber))
                .findFirst().orElseThrow(() -> new InvalidAccountException(INVALID_ACCOUNT.value));
    }

    @Override
    public Customer getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber) throws InvalidAccountException {
        return customers.stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber) && p.getPin().equals(pinNumber))
                .findFirst()
                .orElseThrow(() -> new InvalidAccountException(LOGIN_INVALID.value));
    }

    @Override
    public Customer updateCustomer(Customer customer) throws InvalidAccountException {
        customers.removeIf(p -> p.getAccountNumber().equals(customer.getAccountNumber()));
        customers.add(customer);
        return getCustomerByAccountNumber(customer.getAccountNumber());
    }

    @Override
    public Customer doLogin(String accountNumber, String pinNumber) throws InvalidAccountException {
        return getCustomerByAccountAndPinNumber(accountNumber, pinNumber);
    }
}
