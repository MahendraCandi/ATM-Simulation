package com.mahendracandi.mitrais_atm_simulation.model;

import com.mahendracandi.mitrais_atm_simulation.appEnum.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private TransactionType transactionType;
    private Customer customer;
    private LocalDateTime date;
    private BigDecimal amount;
    private Customer destinationAccount;
    private String referenceNumber;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Customer destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", customer=" + customer +
                ", date=" + date +
                ", amount=" + amount +
                ", destinationAccount=" + destinationAccount +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }

    public String toString(TransactionType transactionType) {
        String value;
        if (TransactionType.WITHDRAW.equals(transactionType)) {
            value = withdrawTransactionToString();
        } else if (TransactionType.FUND_TRANSFER.equals(transactionType)) {
            value = fundTransferToString();
        } else {
            value = toString();
        }
        return value;
    }

    private String withdrawTransactionToString() {
        return "Summary" +
                "\nDate     : "  + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")) +
                "\nWithdraw : $" + amount +
                "\nBalance  : $" + (customer.getBalance().intValue() - amount.intValue());
    }

    private String fundTransferToString() {
        return "Fund Transfer Summary" +
                "\nDestination Account : "  + destinationAccount.getAccountNumber() +
                "\nTransfer Amount     : $" + amount.intValue() +
                "\nReference Number    : "  + referenceNumber +
                "\nBalance             : $" + (customer.getBalance().intValue() - amount.intValue());
    }
}
