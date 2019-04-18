package ru.android2019.testingexample.emailValidator;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowView;

import androidx.test.core.app.ActivityScenario;
import ru.android2019.testingexample.MainActivity;
import ru.android2019.testingexample.R;
import ru.android2019.utils.MyShadowView;

@RunWith(RobolectricTestRunner.class)
//@Config(shadows = {MyShadowView.class})
public class RobolectricShadowTest {
    ActivityScenario<MainActivity> activityScenario;
    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void settingsClick() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        View viewById = mainActivity.findViewById(R.id.settings);
        ShadowView.dump(viewById);
        ShadowView.clickOn(viewById);
    }
}
