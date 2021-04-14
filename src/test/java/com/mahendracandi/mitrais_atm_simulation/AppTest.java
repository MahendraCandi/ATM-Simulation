package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private static final CustomerService customerService = new CustomerServiceImpl();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testGetAllCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        Assert.assertTrue(customerList.size() > 0);
    }

    public void testGetCustomerByAccountNumber() {
        Customer customer = customerService.getCustomerByAccountNumber("112233");
        Assert.assertEquals("John Doe", customer.getName());
    }

    public void testUpdateCustomer() {
        Customer customer = customerService.getCustomerByAccountNumber("112244");
        customer.setName("Tamara");
        customerService.updateCustomer(customer);

        Assert.assertEquals("Tamara", customerService.getCustomers()
                .stream()
                .filter(p -> p.getAccountNumber().equals("112244"))
                .findFirst().get().getName());
    }
}
