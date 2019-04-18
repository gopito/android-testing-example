package ru.android2019.testingexample.emailValidator;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Calendar;

import androidx.test.core.app.ApplicationProvider;

import static android.os.Build.VERSION_CODES.M;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.android2019.testingexample.emailValidator.SharedPreferencesHelper.KEY_DOB;
import static ru.android2019.testingexample.emailValidator.SharedPreferencesHelper.KEY_EMAIL;
import static ru.android2019.testingexample.emailValidator.SharedPreferencesHelper.KEY_NAME;

@RunWith(RobolectricTestRunner.class)
    @Config(minSdk=M)
    public class RobolectricSharedPreferencesHelperTest {
    private static final String TEST_NAME = "Test name";

    private static final String TEST_EMAIL = "test@email.com";

    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();

    static {
        TEST_DATE_OF_BIRTH.set(1980, 1, 1);
    }

    private SharedPreferenceEntry mSharedPreferenceEntry;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    private SharedPreferences mSharedPreferences;

        @Test
        public void addsDataToSharedPreference() {
            Application application = ApplicationProvider.getApplicationContext();
            mSharedPreferences =  PreferenceManager.getDefaultSharedPreferences(application);
            mSharedPreferenceEntry = new SharedPreferenceEntry(TEST_NAME, TEST_DATE_OF_BIRTH, TEST_EMAIL);
            mSharedPreferencesHelper = new SharedPreferencesHelper(mSharedPreferences);
            boolean success = mSharedPreferencesHelper.savePersonalInfo(mSharedPreferenceEntry);
            assertThat("Checking that SharedPreferenceEntry.save... returns true",
                    success, is(true));

            // Read personal information from SharedPreferences
            SharedPreferenceEntry savedSharedPreferenceEntry =
                    mSharedPreferencesHelper.getPersonalInfo();


            // Make sure both written and retrieved personal information are equal.
            assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                    mSharedPreferenceEntry.getName(),
                    is(equalTo(savedSharedPreferenceEntry.getName())));
            assertThat("Checking that SharedPreferenceEntry.dateOfBirth has been persisted and read "
                            + "correctly",
                    mSharedPreferenceEntry.getDateOfBirth(),
                    is(equalTo(savedSharedPreferenceEntry.getDateOfBirth())));
            assertThat("Checking that SharedPreferenceEntry.email has been persisted and read "
                            + "correctly",
                    mSharedPreferenceEntry.getEmail(),
                    is(equalTo(savedSharedPreferenceEntry.getEmail())));

        }

    public SharedPreferenceEntry getPersonalInfo() {
        // запрашиваем данные из SharedPreferences.
        String name = mSharedPreferences.getString(KEY_NAME, "");
        Long dobMillis =
                mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTimeInMillis(dobMillis);
        String email = mSharedPreferences.getString(KEY_EMAIL, "");

        // Возвращаем SharedPreferenceEntry объект.
        return new SharedPreferenceEntry(name, dateOfBirth, email);
    }
}
