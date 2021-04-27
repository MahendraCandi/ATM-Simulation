package com.mahendracandi.mitrais_atm_simulation.util;

import java.util.List;
import java.util.stream.Collectors;

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

    public String joinMessages(List<String> messages) {
        return messages.stream().map(this::addDelimiter).collect(Collectors.joining());
    }

    public static void printAllErrorMessage(String responseMessage) {
        for (String message : responseMessage.split(MessageUtil.MESSAGE_DELIMITER)) {
            MessageUtil.printInvalidMessage(message);
        }
    }

    public static void printAllErrorMessage(List<String> responseMessages) {
        printAllErrorMessage(new MessageUtil().joinMessages(responseMessages));
    }

    public static String extractMessage(List<String> messages) {
        return String.join("\n", messages).replaceAll("\n$", "");
    }
}
