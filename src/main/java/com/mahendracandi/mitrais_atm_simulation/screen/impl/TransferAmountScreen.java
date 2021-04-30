package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidAmountException;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.appenum.ValidationResult;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.AmountValidator;

import java.math.BigDecimal;
import java.util.Optional;

public class TransferAmountScreen extends Screen {
    private BigDecimal transferAmount;

    @Override
    public void showScreen() throws NumberFormatException, InvalidAmountException {
        MessageUtil.printMessage("Please enter transfer amount and\n" +
                "press enter to continue or\n" +
                "Press enter to go back to Transaction: ");
        String transferAmount = doInput();
        if (transferAmount.isEmpty()) return;
        BigDecimal amount;
        try {
            amount = new BigDecimal(transferAmount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ValidationResult.INVALID_AMOUNT.value);
        }
        AmountValidator amountValidator = new AmountValidator();
        amountValidator.validate(amount);

        this.transferAmount = amount;
    }

    public Optional<BigDecimal> getTransferAmount() {
        return transferAmount == null ? Optional.empty() : Optional.of(transferAmount);
    }
}
