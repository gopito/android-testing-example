package ru.android2019.testingexample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static java.lang.Thread.sleep;

public class TestAnnotaionParameters {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = Calculator.getInstance();
    }

    //тест будет работать не больше секунды, а потом упадет
    @Test(timeout = 1000)
    public void longTest() {

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Аннотация для перехвата исключения
    @Test(expected = IllegalStateException.class)
    public void additionWithNull() {
        calculator.add(3, null);
    }

    //Тест не будет выполняться
    @Ignore("Разберусь потом")
    @Test
    public void somethingStrange() {
        final Random random = new Random();
        int i = random.nextInt();
        Assert.assertTrue("Рандом меньше 5. random = " + i, i >= 5);

    }
}
