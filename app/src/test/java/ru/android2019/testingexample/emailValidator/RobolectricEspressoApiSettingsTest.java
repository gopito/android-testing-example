package ru.android2019.testingexample.emailValidator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.runner.AndroidJUnit4;
import ru.android2019.testingexample.MainActivity;
import ru.android2019.testingexample.R;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RobolectricEspressoApiSettingsTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void settingsButtonClick() {
        Intents.init();
        onView(withId(R.id.settings)).perform(click());
        intended(hasComponent(SettingsActivity.class.getName()));
    }
}

