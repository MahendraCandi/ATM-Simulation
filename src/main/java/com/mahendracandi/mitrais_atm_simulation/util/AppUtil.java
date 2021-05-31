package com.mahendracandi.mitrais_atm_simulation.util;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public <T> List<T> getDuplicateItemOnList(List<T> list) {
        return this.getDuplicateItemOnList(list.stream());
    }

    public <T> List<T> getDuplicateItemOnList(Stream<T> stream) {
        Set<T> set = new HashSet<>();
        return stream.filter(p -> !set.add(p)).collect(Collectors.toList());
    }

    public String getAbsolutePathFromResourcesDirectory(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return file.getAbsolutePath().replaceAll("%20", " ");
    }
}
