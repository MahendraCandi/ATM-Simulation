package com.mahendracandi.mitrais_atm_simulation;

import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();
    private static Customer customer;

    public static void main( String[] args )
    {
        boolean exit = false;
        while (!exit) {
            welcomeScreen();
        }
    }

    private static void welcomeScreen() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        boolean isCredentialValid = false;

        if (isAccountNumberValid(accountNumber)) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            if (isPinValid(pin)) {
                isCredentialValid = isCredentialValid(accountNumber, pin);
            }
        }

        if (!isCredentialValid) {
            System.out.println("Invalid Account Number/PIN");
        } else {
            transactionScreen();
        }
    }

    private static void transactionScreen () {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
        String option = scanner.nextLine();
        if (option.isEmpty()) option = "3";
        switch (option) {
            case "1":
                withdrawScreen();
                break;
            case "2":
                fundTransfer();
                break;
            case "3":
                welcomeScreen();
                break;
            default:
                transactionScreen();
        }
    }

    private static void withdrawScreen() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.print("Please choose option[5]: ");
        String option = scanner.nextLine();
        if (option.isEmpty()) option = "5";
        String amount;
        switch (option) {
            case "1":
                amount = "10";
                if (calculateBalance(amount)) summaryScreen(amount);
                else withdrawScreen();
                break;
            case "2":
                amount = "50";
                if (calculateBalance(amount)) summaryScreen(amount);
                else withdrawScreen();
                break;
            case "3":
                amount = "100";
                if (calculateBalance(amount)) summaryScreen(amount);
                else withdrawScreen();
                break;
            case "4":
                System.out.println("Other Withdraw");
                System.out.print("Enter amount to withdraw: ");
                amount = scanner.nextLine();
                if (calculateBalance(amount)) summaryScreen(amount);
                else withdrawScreen();
                break;
            case "5":
                transactionScreen();
                break;
            default:
                withdrawScreen();
        }
    }

    private static boolean calculateBalance(String amount) {
        if (checkAmountValidation(amount)) {
            // deduct balance by amount
            int deductedBalance = customer.getBalance().intValue() - Integer.parseInt(amount);
            customer.setBalance(new BigDecimal(deductedBalance));

            // save customer
            customer = customerService.updateCustomer(customer);
            return true;
        }
        return false;
    }

    private static void summaryScreen(String amount) {
        System.out.println("Summary");
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        System.out.println("Date : " + date.format(dtf));
        System.out.println("Withdraw : $" + amount);
        System.out.println("Balance : $" + customer.getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
        String option = scanner.nextLine();
        if(option.isEmpty()) option = "2";
        switch (option) {
            case "1":
                transactionScreen ();
                break;
            case "2":
                welcomeScreen();
            default:
                summaryScreen(amount);
        }
    }

    private static void fundTransfer() {
        // screen 1
        String destinationAccount = fundTransferScreen1();
        // screen 2
        String amount = fundTransferScreen2();
        // screen 3
        String inputReference = fundTransferScreen3();
        // screen 4
        boolean confirmTrx = fundTransferScreen4(destinationAccount, amount, inputReference);

        if (confirmTrx) {
            calculateBalance(amount);
            fundTransferSummary(destinationAccount, amount, inputReference);
        } else {
            transactionScreen();
        }
    }

    private static String fundTransferScreen1() {
        System.out.println("Please enter destination account and press enter to continue or");
        System.out.print("press enter to go back to Transaction: ");
        String destinationAccount = scanner.nextLine();
        if (destinationAccount.isEmpty()) transactionScreen();
        if (!isOnlyNumbers(destinationAccount) &&
                customerService.getCustomerByAccountNumber(destinationAccount) == null) {
            System.out.println("Invalid account");
            destinationAccount = fundTransferScreen1();
        } else {
            return destinationAccount;
        }
        return destinationAccount;
    }

    private static String fundTransferScreen2() {
        System.out.println("Please enter transfer amount and");
        System.out.println("press enter to continue or");
        System.out.print("press enter to go back to Transaction: ");
        String amount = scanner.nextLine();
        if (amount.isEmpty()) transactionScreen();
        if (checkAmountTransferValidation(amount)) {
            return amount;
        } else amount = fundTransferScreen2();
        return amount;
    }

    private static String fundTransferScreen3() {
        int referenceNumber = getReferenceNumber();
        System.out.println("Reference Number [" + referenceNumber + "]: ");
        System.out.print("Press enter to continued ");
        String inputReference = scanner.nextLine();
        inputReference = inputReference.isEmpty() ? String.valueOf(referenceNumber) : inputReference;
        if (!inputReference.isEmpty() && isOnlyNumbers(inputReference)) {
            return inputReference;
        } else {
            System.out.println("Invalid Reference Number");
            inputReference = fundTransferScreen3();
        }
        return inputReference;
    }

    private static boolean fundTransferScreen4(String destinationAccount, String amount, String inputReference) {
        System.out.println("Transfer Confirmation");
        System.out.println("Destination account: " + destinationAccount);
        System.out.println("Transfer amount: $" + amount);
        System.out.println("Reference number: " + inputReference);
        System.out.println("1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.print("Choose Option[2]: ");
        String option = scanner.nextLine();
        boolean valid = false;
        switch (option) {
            case "1":
                valid = true;
                break;
            case "2":
                break;
            default:
                fundTransferScreen4(destinationAccount, amount, inputReference);
        }
        return valid;
    }

    private static void fundTransferSummary(String destinationAccount, String amount, String referenceNumber) {
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination account: " + destinationAccount);
        System.out.println("Transfer amount: $" + amount);
        System.out.println("Reference number: " + referenceNumber);
        System.out.println("Balance: $" + customer.getBalance().intValue());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose Option[2]: ");
        String option = scanner.nextLine();
        if (option.isEmpty()) option = "2";
        switch (option) {
            case "1":
                transactionScreen();
                break;
            case "2":
                welcomeScreen();
                break;
            default:
                fundTransferSummary(destinationAccount, amount, referenceNumber);
        }

    }

    private static boolean isLengthValid (String value) {
        return value.length() == 6;
    }

    private static boolean isOnlyNumbers (String value) {
        return value.matches("\\d+");
    }

    private static boolean isAccountNumberValid (String accountNumber) {
        if (!isLengthValid(accountNumber)) {
            System.out.println("Account Number should have 6 digits");
        } else if (!isOnlyNumbers(accountNumber)) {
            System.out.println("Account Number should only contains numbers");
        } else {
            return true;
        }
        return false;
    }

    private static boolean isPinValid (String pin) {
        if (!isLengthValid(pin)) {
            System.out.println("Pin should have 6 digits");
        } else if (!isOnlyNumbers(pin)) {
            System.out.println("Pin should only contains numbers");
        } else {
            return true;
        }
        return false;
    }

    private static boolean isCredentialValid (String accountNumber, String pin) {
        Customer  c = customerService.getCustomerByAccountNumber(accountNumber);
        if (c != null) {
            customer = c;
            return c.getPin().equalsIgnoreCase(pin);
        }
        return false;
    }

    private static boolean checkAmountValidation (String value) {
        int amount;
        if (!value.matches("\\d+")) {
            System.out.println("Invalid ammount");
        } else {
            amount = Integer.parseInt(value);
            if (amount > 1000) {
                System.out.println("Maximum amount to withdraw is $1000");
            } else if (amount % 10 != 0) {
                System.out.println("Invalid ammount");
            } else if (customer.getBalance().toBigInteger().intValue() < amount) {
                System.out.printf("Insufficient balance $%d", amount);
                System.out.println();
            } else {
                return true;
            }
        }
        return false;
    }


    private static boolean checkAmountTransferValidation(String value) {
        int amount;
        if (!value.matches("\\d+")) {
            System.out.println("Invalid ammount");
        } else {
            amount = Integer.parseInt(value);
            if (amount > 1000) {
                System.out.println("Maximum amount to withdraw is $1000");
            } else if (amount < 1) {
                System.out.println("Minimum amount to withdraw is $1");
            } else if (customer.getBalance().toBigInteger().intValue() < amount) {
                System.out.printf("Insufficient balance $%d", amount);
                System.out.println();
            } else {
                return true;
            }
        }
        return false;
    }

    private static int getReferenceNumber() {
        int min = 100000;
        int max = 999999;
        return (int) (Math.random()*((max-min)+1))+min;
    }
}
