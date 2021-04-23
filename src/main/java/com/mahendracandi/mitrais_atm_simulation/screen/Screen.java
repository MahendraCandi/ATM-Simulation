package com.mahendracandi.mitrais_atm_simulation.screen;

import java.util.Scanner;

public abstract class Screen {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void showScreen();

    public String doInput() {
        return this.scanner.nextLine();
    }

    public String doInput(String defaultInput) {
        if (defaultInput == null || defaultInput.isEmpty()) throw new IllegalArgumentException();

        String scanInput = doInput();
        return (scanInput == null || scanInput.isEmpty()) ? defaultInput : scanInput;
    }

}
