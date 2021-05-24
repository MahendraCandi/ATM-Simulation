package com.mahendracandi.mitrais_atm_simulation.service;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.model.TransactionHistory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistoryServiceImpl implements TransactionHistoryService{
    private static List<TransactionHistory> HISTORIES = new ArrayList<>();

    @Override
    public List<TransactionHistory> getAllHistoryByAccountNumber(String accountNumber) {
        return HISTORIES.stream().filter(p -> accountNumber.equals(p.getAccountNumber()))
                .sorted(Comparator.comparingInt(TransactionHistory::getIndex))
                .collect(Collectors.toList());
    }

    @Override
    public void saveHistory(Transaction transaction, Customer customer, Customer destinationAccount) {
        if (TransactionType.FUND_TRANSFER.equals(transaction.getTransactionType())) {
            saveFundTransferHistory(transaction, customer);
            saveDepositHistory(transaction, destinationAccount);
        } else {
            saveWithdrawHistory(transaction, customer);
        }
    }

    private void saveWithdrawHistory(Transaction transaction, Customer customer) {
        TransactionHistory tHistory = new TransactionHistory();
        tHistory.setAccountNumber(customer.getAccountNumber());
        tHistory.setRecord(toStringAsWithdrawHistory(transaction, customer));
        tHistory.setIndex(getLastIndexFromHistories());
        HISTORIES.add(tHistory);
    }

    private void saveFundTransferHistory(Transaction transaction, Customer customer) {
        TransactionHistory tHistory = new TransactionHistory();
        tHistory.setAccountNumber(customer.getAccountNumber());
        tHistory.setRecord(toStringAsFundTransferHistory(transaction, customer));
        tHistory.setIndex(getLastIndexFromHistories());
        HISTORIES.add(tHistory);
    }

    private void saveDepositHistory(Transaction transaction, Customer destinationAccount) {
        TransactionHistory tHistory = new TransactionHistory();
        tHistory.setAccountNumber(destinationAccount.getAccountNumber());
        tHistory.setRecord(toStringAsDepositHistory(transaction, destinationAccount));
        tHistory.setIndex(getLastIndexFromHistories());
        HISTORIES.add(tHistory);
    }

    public String toStringAsWithdrawHistory(Transaction transaction, Customer customer) {
        return  "Withdraw" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nWithdraw : $" + transaction.getAmount().intValue() +
                "\nBalance  : $" + getEndingBalance(customer);
    }

    public String toStringAsFundTransferHistory(Transaction transaction, Customer customer) {
        return "Fund Transfer" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nDestination Account : "  + transaction.getDestinationAccount().getAccountNumber() +
                "\nTransfer Amount     : $" + transaction.getAmount().intValue() +
                "\nReference Number    : "  + transaction.getReferenceNumber() +
                "\nBalance             : $" + getEndingBalance(customer);
    }

    private String toStringAsDepositHistory(Transaction transaction, Customer destinationAccount) {
        return "Deposit Transfer" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nSender Account : "  + transaction.getCustomer().getAccountNumber() +
                "\nTransfer Amount     : $" + transaction.getAmount().intValue() +
                "\nReference Number    : "  + transaction.getReferenceNumber() +
                "\nBalance             : $" + getEndingBalance(destinationAccount);
    }

    private Integer getEndingBalance(Customer customer) {
        return customer.getBalance().intValue();
    }

    private int getLastIndexFromHistories() {
        return HISTORIES.size() + 1;
    }
}
