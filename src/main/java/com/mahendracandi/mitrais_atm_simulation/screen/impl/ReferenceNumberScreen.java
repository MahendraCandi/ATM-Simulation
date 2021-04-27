package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.model.ValidationMessage;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.transaction.ReferenceNumberValidatorImpl;

import java.util.Optional;

public class ReferenceNumberScreen extends Screen {
    private final AppUtil appUtil = new AppUtil();
    private String referenceNumber;

    @Override
    public void showScreen() {
        int randomNumber = appUtil.getRandomNumber();
        MessageUtil.printMessage("Reference Number: (" + randomNumber + ")\n" +
                "Press enter to continued ");
        String referenceNumber = doInput(String.valueOf(randomNumber));
        ValidationMessage vMessage = new ReferenceNumberValidatorImpl().validateNumber(referenceNumber);
        if (vMessage.isNotSuccess()) {
            MessageUtil.printAllErrorMessage(vMessage.getErrorMessages());
        } else this.referenceNumber = referenceNumber;
    }

    public Optional<String> getReferenceNumber() {
        return referenceNumber == null ? Optional.empty() : Optional.of(referenceNumber);
    }
}