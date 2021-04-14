package com.mahendracandi.mitrais_atm_simulation.model;

import java.math.BigDecimal;

public class FundTransfer {
    private String destinationAccount;
    private String transferAmount;
    private String referenceNumber;
    private Customer destinationCustomer;

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Customer getDestinationCustomer() {
        return destinationCustomer;
    }

    public void setDestinationCustomer(Customer destinationCustomer) {
        this.destinationCustomer = destinationCustomer;
    }

    @Override
    public String toString() {
        return "FundTransfer{" +
                "destinationAccount='" + destinationAccount + '\'' +
                ", transferAmount='" + transferAmount + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", destinationCustomer=" + destinationCustomer +
                '}';
    }

    public String toConfirmationString() {
        return "Transfer Confirmation" +
                "\nDestination Account: " + destinationAccount +
                "\nTransfer Amount    : $" + transferAmount +
                "\nReference Number   : " + referenceNumber;
    }
}
