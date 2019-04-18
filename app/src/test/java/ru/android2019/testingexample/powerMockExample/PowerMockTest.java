package ru.android2019.testingexample.powerMockExample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.android2019.testingexample.emailValidator.EmailValidator;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(PowerMockRunner.class)
@PrepareForTest(EmailValidator.class)
public class PowerMockTest {
    @Test
    public void powerMockTest() {
        PowerMockito.mockStatic(EmailValidator.class);
        //смогли замокать статический метод
        PowerMockito.when(EmailValidator.isValidEmail(anyString())).thenReturn(true);
        assertTrue(EmailValidator.isValidEmail("not real email"));
    }
}