package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.appenum.TransactionType;
import com.mahendracandi.mitrais_atm_simulation.model.Customer;
import com.mahendracandi.mitrais_atm_simulation.model.Transaction;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
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

    public WithdrawScreen(Customer customer) {
        this.customer = customer;
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
                    case "1":
                        amountStr= "10";
                        break;
                    case "2":
                        amountStr= "50";
                        break;
                    case "3":
                        amountStr= "100";
                        break;
                    case "4":
                        MessageUtil.printMessage("Other Withdraw");
                        MessageUtil.printMessage("Enter amount to withdraw: ");
                        amountStr = doInput();
                        break;
                    case "5":
                        exitLoop = true;
                        break;
                }
                if (amountStr != null) {
                    BigDecimal amount = new BigDecimal(amountStr);
                    List<AppValidator<BigDecimal>> validators = new ArrayList<>();
                    validators.add(new AmountValidator());
                    validators.add(new CustomerBalanceValidator(customer));
                    for (AppValidator<BigDecimal> validator : validators) {
                        validator.validate(amount);
                    }
                    Transaction transaction = new Transaction();
                    transaction.setTransactionType(TransactionType.WITHDRAW);
                    transaction.setCustomer(customer);
                    transaction.setAmount(amount);
                    transaction.setDate(LocalDateTime.now());
                    SummaryScreen summaryScreen = new SummaryScreen(transaction);
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
}
