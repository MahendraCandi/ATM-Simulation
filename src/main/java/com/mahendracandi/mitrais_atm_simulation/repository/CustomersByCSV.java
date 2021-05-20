package com.mahendracandi.mitrais_atm_simulation.repository;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.DuplicateAccountValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.DuplicateRecordValidator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersByCSV implements CustomerRepository {

    private List<Customer> customers;

    public CustomersByCSV(String csvPath) {
        try {
            this.customers = initialize(csvPath);
        } catch (Exception e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customers;
    }

    private List<Customer> initialize(String csvPath) throws Exception {
        Path path = Paths.get(csvPath);

        List<String> records = generateCsvRecordAsListOfString(path);
        AppValidator<List<String>> recordValidator = new DuplicateRecordValidator();
        recordValidator.validate(records);

        List<Customer> customers = generateCustomersFromCsvRecord(records);
        AppValidator<List<Customer>> duplicateAccountValidator = new DuplicateAccountValidator();
        duplicateAccountValidator.validate(customers);

        return customers;
    }

    public List<String> generateCsvRecordAsListOfString(Path path) throws IOException {
        return Files.lines(path).collect(Collectors.toList());
    }

    public List<Customer> generateCustomersFromCsvRecord(List<String> records) {
        List<Customer> customers;
        customers = records.stream().map(p -> {
            String[] strings = p.split(",");
            return new Customer(strings[0], strings[1], strings[3], new BigDecimal(strings[2]));
        }).collect(Collectors.toList());
        return customers;
    }

}
