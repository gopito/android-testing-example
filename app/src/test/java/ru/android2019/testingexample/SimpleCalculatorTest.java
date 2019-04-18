package ru.android2019.testingexample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.android2019.utils.DigitUtils;

import static ru.android2019.utils.DigitUtils.SEVEN;

public class SimpleCalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = Calculator.getInstance();
    }

    @Test
    public void additionTest() {
        calculator.add(2, 3);
        Assert.assertEquals(5, calculator.getResult());
    }

    @Test
    public void additionZeroTest() {
        calculator.add(2, 0);
        Assert.assertEquals(2, calculator.getResult());
    }


    @Test
    public void subtractionTestWithUtil() {
        calculator.subtract(DigitUtils.ten(), SEVEN);
        Assert.assertEquals(3, calculator.getResult());
    }
}
