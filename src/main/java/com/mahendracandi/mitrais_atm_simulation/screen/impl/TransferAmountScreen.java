package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.transaction.AmountValidatorImpl;

import java.math.BigDecimal;
import java.util.Optional;

public class TransferAmountScreen extends Screen {
    private BigDecimal transferAmount;

    @Override
    public void showScreen() {
        MessageUtil.printMessage("Please enter transfer amount and\n" +
                "press enter to continue or\n" +
                "Press enter to go back to Transaction: ");
        String transferAmount = doInput();
        if (transferAmount.isEmpty()) return;
        ValidationMessage vMessage = new AmountValidatorImpl().validate(transferAmount);
        if (vMessage.isNotSuccess()) {
            MessageUtil.printAllErrorMessage(vMessage.getErrorMessages());
        } else {
            this.transferAmount = new BigDecimal(transferAmount);
        }
    }

    public Optional<BigDecimal> getTransferAmount() {
        return transferAmount == null ? Optional.empty() : Optional.of(transferAmount);
    }
}
