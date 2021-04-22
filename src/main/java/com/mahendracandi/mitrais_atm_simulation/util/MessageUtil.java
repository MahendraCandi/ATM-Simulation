package com.mahendracandi.mitrais_atm_simulation.util;

public class MessageUtil {

    public final static String MESSAGE_DELIMITER = ";";

    public static void printMessage (String message) {
        System.out.println(message);
    }

    public static void printInvalidMessage(String message) {
        System.err.println(message);
    }

    public static void printInvalidMessage(String message, int value) {
        System.err.printf(message + "\n", value);
    }

    public String addDelimiter(String message) {
        return message + MESSAGE_DELIMITER;
    }

    public String deleteLastDelimiter(String message) {
        return message.replaceAll(";$", "");
    }
}
