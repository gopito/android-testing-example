package ru.android2019.testingexample;

import androidx.core.util.Preconditions;

public class Calculator {
    private Integer result;
    private Integer firstDigit;
    private Integer secondDigit;
    private String error;
    private static final Calculator instance = new Calculator();

    private Calculator() {
        firstDigit = 0;
        secondDigit = 0;
        result = 0;
    }

    public static Calculator getInstance() {
        return instance;
    }

    public Calculator add(Integer first, Integer second) {
        if (second == null) {
            throw new IllegalStateException("Second argument should not be null");
        }
        error = "";
        firstDigit = first;
        secondDigit = second;
        result = firstDigit + secondDigit;
        return this;
    }

    public Calculator subtract(Integer first, Integer second) {
        error = "";
        firstDigit = first;
        secondDigit = second;
        result = firstDigit - secondDigit;
        return this;
    }

    public Calculator multiply(Integer first, Integer second) {
        error = "";
        firstDigit = first;
        secondDigit = second;
        result = firstDigit * secondDigit;
        return this;
    }

    public Calculator divide(Integer first, Integer second) {
        error = "";
        firstDigit = first;
        secondDigit = second;
        if (secondDigit.equals(0)) {
            error = "На ноль делить нельзя";
            return this;
        }
        result = firstDigit / secondDigit;
        return this;
    }

    public Integer convertDigit(String text) {
        return Integer.valueOf(text.isEmpty() ? "0" : text);
    }

    public String getError() {
        return error;
    }

    public int getResult() {
        return result;
    }

    public int getFirstDigit() {
        return firstDigit;
    }

    public int getSecondDigit() {
        return secondDigit;
    }
}
