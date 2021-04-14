package com.mahendracandi.mitrais_atm_simulation.util;

public class MessageUtil {

    public static void printMessage (String message) {
        System.out.println(message);
    }

    public static void printInvalidMessage(String message) {
        System.err.println(message);
    }

    public static void printInvalidMessage(String message, int value) {
        System.err.printf(message + "\n", value);
    }
}
