package com.mahendracandi.mitrais_atm_simulation.screen;

import java.util.Scanner;

public abstract class Screen {
    protected String defaultInput = "";
    protected Scanner scanner = new Scanner(System.in);

    public abstract void showScreen();

    public String doInput() {
        String scanInput = this.scanner.nextLine();
        if (scanInput == null || scanInput.isEmpty()) return getDefaultInput();
        return scanInput;
    }

    protected void setDefaultInput(String defaultInput) {
        this.defaultInput = defaultInput;
    }

    protected String getDefaultInput() {
        return this.defaultInput;
    }

}
