package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAccountException;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService{

    private final CustomerService customerService;
    private final TransactionHistoryService historyService;

    public TransactionServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
        this.historyService = new TransactionHistoryServiceImpl();
    }

    @Override
    public void doTransaction(TransactionType transactionType, Customer customer, BigDecimal amount, LocalDateTime dateTime) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(dateTime);

        this.doTransaction(transaction);
    }

    @Override
    public void doTransaction(Transaction transaction) {
        Customer customer = transaction.getCustomer();
        BigDecimal deductedBalance = deductedBalance(customer.getBalance(), transaction.getAmount());
        customer.setBalance(deductedBalance);
        try {
            customerService.updateCustomer(customer);

            Customer destinationAccount = new Customer();
            if (TransactionType.FUND_TRANSFER.equals(transaction.getTransactionType())) {
                destinationAccount = transaction.getDestinationAccount();
                destinationAccount.setBalance(addBalance(destinationAccount.getBalance(), transaction.getAmount()));
                customerService.updateCustomer(destinationAccount);
            }

            historyService.saveHistory(transaction, customer, destinationAccount);
        } catch (InvalidAccountException e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }

    private BigDecimal deductedBalance(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }

    private BigDecimal addBalance(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount);
    }
}
