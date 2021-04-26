package com.mahendracandi.mitrais_atm_simulation.model;

import java.util.List;

public class ValidationMessage {
    private boolean isNotSuccess;
    private List<String> errorMessages;

    public boolean isNotSuccess() {
        return isNotSuccess;
    }

    public void setNotSuccess(boolean notSuccess) {
        isNotSuccess = notSuccess;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
