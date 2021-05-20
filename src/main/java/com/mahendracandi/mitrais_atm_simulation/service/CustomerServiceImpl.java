package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.repository.CustomerRepository;
import com.mahendracandi.mitrais_atm_simulation.repository.DefaultCustomers;

import java.util.List;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INVALID_ACCOUNT;
import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.LOGIN_INVALID;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl() {
        this.customerRepository = new DefaultCustomers();
    }

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerByAccountNumber(String accountNumber) throws InvalidAccountException {
        return getCustomers().stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber))
                .findFirst().orElseThrow(() -> new InvalidAccountException(INVALID_ACCOUNT.value));
    }

    @Override
    public Customer getCustomerByAccountAndPinNumber(String accountNumber, String pinNumber) throws InvalidAccountException {
        return getCustomers().stream()
                .filter(p -> p.getAccountNumber().equals(accountNumber) && p.getPin().equals(pinNumber))
                .findFirst()
                .orElseThrow(() -> new InvalidAccountException(LOGIN_INVALID.value));
    }

    @Override
    public Customer updateCustomer(Customer customer) throws InvalidAccountException {
        getCustomers().removeIf(p -> p.getAccountNumber().equals(customer.getAccountNumber()));
        getCustomers().add(customer);
        return getCustomerByAccountNumber(customer.getAccountNumber());
    }

    @Override
    public Customer doLogin(String accountNumber, String pinNumber) throws InvalidAccountException {
        return getCustomerByAccountAndPinNumber(accountNumber, pinNumber);
    }
}
