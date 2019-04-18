package ru.android2019.testingexample.mockitoSimpleExample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MockitoInjectMockTest {
    @Mock
    Map<String, String> wordMap;

    //автоматически подставляем wordMap в MyDictionary
    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Before
    public void initMocks() {
        //Необходимая инициализация
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", dic.getMeaning("aWord"));
    }

    public class MyDictionary {
        Map<String, String> wordMap;

        public MyDictionary() {
            wordMap = new HashMap<String, String>();
        }

        public void add(final String word, final String meaning) {
            wordMap.put(word, meaning);
        }

        public String getMeaning(final String word) {
            return wordMap.get(word);
        }
    }
}
