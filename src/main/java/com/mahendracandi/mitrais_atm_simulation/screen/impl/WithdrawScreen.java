package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.service.CustomerService;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.AppValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.AmountValidator;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.CustomerBalanceValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult.INVALID_AMOUNT;

public class WithdrawScreen extends Screen {

    private final Customer customer;
    private final CustomerService customerService;

    private static final String TEN_MENU = "1";
    private static final String FIFTY_OPTION = "2";
    private static final String ONE_HUNDRED_OPTION = "3";
    private static final String OTHERS_OPTION = "4";
    private static final String BACK_OPTION = "5";
    private static final String TEN_DOLLAR = "10";
    private static final String FIFTY_DOLLAR = "50";
    private static final String HUNDRED_DOLLAR = "100";

    public WithdrawScreen(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;
    }

    @Override
    public void showScreen() {
        String amountStr = null;
        boolean exitLoop = false;
        try {
            do{
                MessageUtil.printMessage("1. $10");
                MessageUtil.printMessage("2. $50");
                MessageUtil.printMessage("3. $100");
                MessageUtil.printMessage("4. Other");
                MessageUtil.printMessage("5. Back");
                MessageUtil.printMessage("Please choose option[5]: ");
                String input = doInput("5");
                switch (input) {
                    case TEN_MENU:
                        amountStr= TEN_DOLLAR;
                        break;
                    case FIFTY_OPTION:
                        amountStr= FIFTY_DOLLAR;
                        break;
                    case ONE_HUNDRED_OPTION:
                        amountStr= HUNDRED_DOLLAR;
                        break;
                    case OTHERS_OPTION:
                        MessageUtil.printMessage("Other Withdraw");
                        MessageUtil.printMessage("Enter amount to withdraw: ");
                        amountStr = doInput();
                        break;
                    case BACK_OPTION:
                        exitLoop = true;
                        break;
                }
                if (amountStr != null) {
                    BigDecimal amount = validateAmount(amountStr);

                    Transaction transaction = buildTransaction(amount);
                    SummaryScreen summaryScreen = new SummaryScreen(transaction, customerService);
                    summaryScreen.showScreen();
                    exitLoop = true;
                }
            } while (!exitLoop);
        } catch (NumberFormatException e) {
            MessageUtil.printInvalidMessage(INVALID_AMOUNT.value);
        } catch (Exception e) {
            MessageUtil.printInvalidMessage(e.getMessage());
        }
    }

    private Transaction buildTransaction(BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setCustomer(customer);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        return transaction;
    }

    private BigDecimal validateAmount(String amountStr) throws Exception {
        BigDecimal amount = new BigDecimal(amountStr);
        List<AppValidator<BigDecimal>> validators = new ArrayList<>();
        validators.add(new AmountValidator());
        validators.add(new CustomerBalanceValidator(customer));
        for (AppValidator<BigDecimal> validator : validators) {
            validator.validate(amount);
        }
        return amount;
    }
}
