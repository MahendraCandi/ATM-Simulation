package com.mahendracandi.mitrais_atm_simulation.screen.impl;

import com.mahendracandi.mitrais_atm_simulation.exception.InvalidReferenceNumberException;
import com.mahendracandi.mitrais_atm_simulation.screen.Screen;
import com.mahendracandi.mitrais_atm_simulation.util.AppUtil;
import com.mahendracandi.mitrais_atm_simulation.util.MessageUtil;
import com.mahendracandi.mitrais_atm_simulation.validation.impl.ReferenceNumberValidator;

import java.util.Optional;

public class ReferenceNumberScreen extends Screen {
    private final AppUtil appUtil = new AppUtil();
    private String referenceNumber;

    @Override
    public void showScreen() throws InvalidReferenceNumberException {
        int randomNumber = appUtil.getRandomNumber();
        MessageUtil.printMessage("Reference Number: (" + randomNumber + ")\n" +
                "Press enter to continued ");
        String referenceNumber = doInput(String.valueOf(randomNumber));
        ReferenceNumberValidator validator = new ReferenceNumberValidator();
        validator.validate(referenceNumber);

        this.referenceNumber = referenceNumber;
    }

    public Optional<String> getReferenceNumber() {
        return referenceNumber == null ? Optional.empty() : Optional.of(referenceNumber);
    }
}