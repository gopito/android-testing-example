package ru.android2019.testingexample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RulesExample {

    //Будет создана временная директория, которая будет уничтожена после теста
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    //Сможем получать информацию о текущем тесте внутри кажого теста
    @Rule
    public TestName name = new TestName();

    //Каждый тест будет ограничен одной секундой
    @Rule
    public final Timeout timeout = new Timeout(1000, TimeUnit.MILLISECONDS);

    //Можем отлавливать исключения внутри тестов.
    //Пример в testExpectedException
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void anotherInfinity() {
        while (true){}
    }

    @Test
    public void testFileWriting() throws IOException {
        final File log = folder.newFile("debug.log");
        final FileWriter logWriter = new FileWriter(log);
        logWriter.append("Hello, ");
        logWriter.append("World!!!");
        logWriter.flush();
        logWriter.close();
    }

    @Test
    public void testExpectedException() throws IOException {
        thrown.expect(NullPointerException.class);
        folder.newFile(null);
    }
}
