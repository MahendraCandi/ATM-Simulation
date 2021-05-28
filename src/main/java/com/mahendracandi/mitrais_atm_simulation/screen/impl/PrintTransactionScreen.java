package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.*;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintTransactionScreen extends Screen {

    private final Customer customer;
    private final TransactionService transactionService;

    public PrintTransactionScreen(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.transactionService = new TransactionServiceImpl(customerService);
    }

    @Override
    public void showScreen() {
        List<Transaction> histories = transactionService
                .getAllTransactionsByAccountNumber(customer.getAccountNumber())
                .stream().sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());
        if (histories.size() == 0) return;

        List<Transaction> rangeHistories = searchTenLastTransaction(histories);
        int number = 1;
        for (Transaction t : rangeHistories) {
            if (isWithdrawTransaction(t)) {
                MessageUtil.printMessage(number + ". " + toStringAsWithdrawHistory(t) + "\n");
            } else {
                if (isDepositTransaction(t)) {
                    MessageUtil.printMessage(number + ". " + toStringAsDepositHistory(t) + "\n");
                } else if (isFundTransferTransaction(t)) {
                    MessageUtil.printMessage(number + ". " + toStringAsFundTransferHistory(t) + "\n");
                }
            }
            number++;
        }
    }

    private boolean isWithdrawTransaction(Transaction t) {
        return TransactionType.WITHDRAW.equals(t.getTransactionType());
    }

    private boolean isFundTransferTransaction(Transaction t) {
        return TransactionType.FUND_TRANSFER.equals(t.getTransactionType());
    }

    private boolean isDepositTransaction(Transaction t) {
        return customer.getAccountNumber().equals(t.getDestinationAccount().getAccountNumber()) &&
                TransactionType.FUND_TRANSFER.equals(t.getTransactionType());
    }

    private List<Transaction> searchTenLastTransaction(List<Transaction> histories) {
        int size = histories.size();
        int fromIndex = size < 10 ? 0 : size - 10;
        return histories.subList(fromIndex, size);
    }

    public String toStringAsWithdrawHistory(Transaction transaction) {
        return  "Withdraw" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nWithdraw : $" + transaction.getAmount().intValue();
    }

    public String toStringAsFundTransferHistory(Transaction transaction) {
        return "Fund Transfer" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nDestination Account : "  + transaction.getDestinationAccount().getAccountNumber() +
                "\nTransfer Amount     : $" + transaction.getAmount().intValue() +
                "\nReference Number    : "  + transaction.getReferenceNumber();
    }

    private String toStringAsDepositHistory(Transaction transaction) {
        return "Deposit Transfer" +
                "\nDate     : "  + transaction.formattedDate() +
                "\nSender Account      : "  + transaction.getCustomer().getAccountNumber() +
                "\nTransfer Amount     : $" + transaction.getAmount().intValue() +
                "\nReference Number    : "  + transaction.getReferenceNumber();
    }
}
