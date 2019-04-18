package ru.android2019.testingexample;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import ru.android2019.utils.DigitUtils;

import static ru.android2019.utils.DigitUtils.SEVEN;

public class FixturesExampleWithRule {
    private Calculator calculator;

    //Правило будет сообщать имя текущего теств
    @Rule
    public TestName name = new TestName();

    //Фикстура будет выполнена один раз перед всеми тестами
    @BeforeClass
    public static void setupClass() {
        System.out.println("BeforeClass FixturesExampleWithRule started");
    }

    //Фикстура будет выполнена перед запуском каждого теста
    //Выведем имя тестового метода и создадим класс калькулятор
    @Before
    public void setUp() {
        System.out.println(String.format("Test name %s started", name.getMethodName()));
        calculator = Calculator.getInstance();
    }


    @Test
    public void multiplicationTest() {
        calculator.multiply(2, 3);
        Assert.assertEquals(6, calculator.getResult());
    }

    @Test
    public void divisionTest() {
        calculator.divide(6, 3);
        Assert.assertEquals(2, calculator.getResult());
    }

    //Фикстура будет выполнена после каждого теста
    @After
    public void tearDown() {
        System.out.println(String.format("Test name %s finished", name.getMethodName()));
    }

    //Фикстура будет выполнена один раз после всех тестов
    @AfterClass
    public static void tearDownClass() {
        System.out.println("BeforeClass FixturesExampleWithRule finished");
    }

}
