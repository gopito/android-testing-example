/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.android2019.testingexample.emailValidator;

import android.content.SharedPreferences;

import java.util.Calendar;

/**
 *  Класс обертка для доступа к {@link SharedPreferences}.
 */
public class SharedPreferencesHelper {

    // Ключи для сохранения в SharedPreferences.
    static final String KEY_NAME = "key_name";
    static final String KEY_DOB = "key_dob_millis";
    static final String KEY_EMAIL = "key_email";

    // Передаем методу ссылку на SharedPreferences
    private final SharedPreferences mSharedPreferences;

    /**
     * Констуктор с параметором (dependency injection) для удобства тестирования.
     *
     * @param sharedPreferences The {@link SharedPreferences} которые будут использоваться в DAO.
     */
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    /**
     * Сохраняет {@link SharedPreferenceEntry} которая содержит информацию о пользователе в
     * {@link SharedPreferences}.
     *
     * @param sharedPreferenceEntry содержит даннве для сохранения в {@link SharedPreferences}.
     * @return {@code true} если сохранение прошло успешно или {@code false}
     *         в противном случае.
     */
    public boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry){
        // Start a SharedPreferences transaction.
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putLong(KEY_DOB, sharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());

        // Коммитим данные из SharedPreferences.Editor в SharedPreferences.
        return editor.commit();
    }

    /**
     * Получаем {@link SharedPreferenceEntry} с данными пользователя из
     * {@link SharedPreferences}.
     *
     * @return запрошенную {@link SharedPreferenceEntry}.
     */
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
