package com.mahendracandi.mitrais_atm_simulation.model;

import java.math.BigDecimal;

public class ScreenHelper {
    private BigDecimal amount;
    private boolean isInputNotValid;
    private boolean isReturnToTransactionScreen;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isInputNotValid() {
        return isInputNotValid;
    }

    public void setInputNotValid(boolean notInputValid) {
        isInputNotValid = notInputValid;
    }

    public boolean isReturnToTransactionScreen() {
        return isReturnToTransactionScreen;
    }

    public void setReturnToTransactionScreen(boolean returnToTransactionScreen) {
        isReturnToTransactionScreen = returnToTransactionScreen;
    }
}
