package ru.android2019.testingexample.mockitoSimpleExample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockitoSpyTest {

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void spyAnnotation() {
        //Добавляем сторочки в лист. Он должен вести себя также как обычный ArrayList.
        spiedList.add("one");
        spiedList.add("one");
        spiedList.add("two");

        //Проверяем, что метод add("one") вызывался два раза
        verify(spiedList,times(2)).add("one");
        verify(spiedList).add("two");

        assertEquals(3, spiedList.size());

        //Переопределяем метод size() для ArrayList
        Mockito.doReturn(100).when(spiedList).size();
        assertEquals(100, spiedList.size());
    }
}
