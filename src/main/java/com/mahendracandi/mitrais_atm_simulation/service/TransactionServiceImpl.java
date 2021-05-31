package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.repository.TransactionRepository;
import com.mahendracandi.mitrais_atm_simulation.repository.TransactionRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService{

    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
        this.transactionRepository = new TransactionRepositoryImpl();
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

        if (TransactionType.FUND_TRANSFER.equals(transaction.getTransactionType())) {
            Customer destinationAccount = transaction.getDestinationAccount();
            destinationAccount.setBalance(addBalance(destinationAccount.getBalance(), transaction.getAmount()));
        }

        transactionRepository.saveTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int maxSize) {
        return transactionRepository.getAllTransactionByAccountNumber(accountNumber)
                .stream()
                .filter(p -> accountNumber.equals(p.getCustomer().getAccountNumber()) ||
                        (accountNumber.equals(p.getDestinationAccount().getAccountNumber()) &&
                                TransactionType.FUND_TRANSFER.equals(p.getTransactionType())))
                .sorted(Comparator.comparing(Transaction::getDate))
                .limit(maxSize)
                .collect(Collectors.toList());
    }

    private BigDecimal deductedBalance(BigDecimal balance, BigDecimal amount) {
        return balance.subtract(amount);
    }

    private BigDecimal addBalance(BigDecimal balance, BigDecimal amount) {
        return balance.add(amount);
    }
}
