package com.mahendracandi.mitrais_atm_simulation.screen;

import java.util.Scanner;

public abstract class Screen {
    protected String input;
    protected boolean existScreen = false;
    protected String defaultInput = "";
    protected Scanner scanner = new Scanner(System.in);

    public abstract void showScreen();

    protected abstract void readInput();

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

    public String getInput() {
        return input;
    }

    public boolean isExistScreen() {
        return existScreen;
    }
}
