package ru.android2019.testingexample.mockitoSimpleExample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MockitoMockTest {
    @Mock
    List<String> mockedList;

    @Before
    public void initMocks() {
        //Необходимая инициализация
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
        mockedList.add("one");
        //ВЫзываем методы определенные в интерфейсе
        Mockito.verify(mockedList).add("one");
        //так как это пустая заглушка, то размер не увеличивается
        assertEquals(0, mockedList.size());



        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
        //то же самое, но с hamcrest'ом
        assertThat(mockedList.size(), is(100));
    }
}
