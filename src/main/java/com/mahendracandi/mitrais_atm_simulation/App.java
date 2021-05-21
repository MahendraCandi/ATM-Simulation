package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.repository.CustomerRepository;
import com.mahendracandi.mitrais_atm_simulation.repository.CustomersByCSV;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.LoginScreen;
import com.mahendracandi.mitrais_atm_simulation.screen.impl.TransactionScreen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;

import java.util.Optional;

public class App {

    public static void main(String[] args) {
        App app = new App();
        String csvPath = app.getAvailablePath(args);
        CustomerService customerService = app.initializeCustomerService(csvPath);
        do {
            LoginScreen loginScreen = new LoginScreen(customerService);
            loginScreen.showScreen();
            Optional<Customer> customer = loginScreen.getCustomer();
            if (customer.isPresent()) {
                TransactionScreen transactionScreen = new TransactionScreen(customer.get(), customerService);
                transactionScreen.showScreen();
            }
        } while (true);
    }

    private CustomerService initializeCustomerService(String csvPath) {
        CustomerRepository customerRepository = new CustomersByCSV(csvPath);
        return new CustomerServiceImpl(customerRepository);
    }

    private String getAvailablePath(String[] args) {
        if (args.length < 1) return "";
        return args[0];
    }
}
