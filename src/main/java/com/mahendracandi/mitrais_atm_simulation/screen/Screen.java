package com.mahendracandi.mitrais_atm_simulation.screen;

import java.util.Scanner;

public abstract class Screen {
    private final Scanner scanner = new Scanner(System.in);

    public abstract void showScreen() throws Exception;

    protected String doInput() {
        String s = "";
        s = this.scanner.nextLine();
        return s;
    }

    protected String doInput(String defaultInput) {
        if (defaultInput == null || defaultInput.isEmpty()) throw new IllegalArgumentException();

        String scanInput = doInput();
        return (scanInput == null || scanInput.isEmpty()) ? defaultInput : scanInput;
    }

    protected void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }
    }

}
