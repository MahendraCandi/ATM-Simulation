package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
}
