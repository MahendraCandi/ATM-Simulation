package com.mahendracandi.mitrais_atm_simulation.util;

public class AppUtil {

    public boolean isLengthSixDigits(String value) {
        return value.length() == 6;
    }

    public boolean isOnlyNumbers (String value) {
        return value.matches("\\d+");
    }

    public boolean isValueMoreThanMaximumAmount(int value) {
        int max = 1000;
        return value > max;
    }

    public boolean isValueLessThanMinimumAmount(int value) {
        int min = 1;
        return value < min;
    }

    public boolean isValueMultipleOfTen(int value) {
        return value % 10 == 0;
    }

    public boolean isValueNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public int getRandomNumber() {
        int max = 999999;
        int min = 100000;
        double random = (Math.random() * ((max - min) + 1)) + min;
        return (int) random;
    }
}
