package ru.android2019.testingexample;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import ru.android2019.testingexample.rules.ClearSharedPreferenceRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RevertSettingsTest {

    public static final String ANTON = "Anton";
    public static final String EMAIL = "email@exmple.com";
    public static final String SOMETHING_WRONG = "Something wrong";

    @Rule
    public ClearSharedPreferenceRule clearSharedPreferenceRule = new ClearSharedPreferenceRule();

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void saveSettingsTest() {
        //Переходим в Settings
        onView(withId(R.id.settings)).perform(click());

        //вбиваем имя
        onView(withId(R.id.userNameInput)).perform(typeText(ANTON), closeSoftKeyboard());
        //вбиваем email
        onView(withId(R.id.emailInput)).perform(typeText(EMAIL), closeSoftKeyboard());
        //сохраняем
        onView(withId(R.id.saveButton)).perform(click());

        //Проверим, что имя откатывается обратно
        //Изменяем имя
        onView(withId(R.id.userNameInput)).perform(clearText()).perform(typeText(SOMETHING_WRONG), closeSoftKeyboard());
        //жмем Revert
        onView(withId(R.id.revertButton)).perform(click());

        //Проверяем, что имя, такое какое надо.
        onView(withId(R.id.userNameInput)).check(matches(withText(ANTON)));
    }
}
