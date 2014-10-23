package com.github.blazsolar.chuck.data.prefs;

import android.content.SharedPreferences;

/**
 * Created by Blaz Solar on 22/10/14.
 */
public class StringPreference {

    private final SharedPreferences preferences;
    private final String key;
    private final String defaultValue;

    public StringPreference(String key, SharedPreferences preferences) {
        this(preferences, key, null);
    }

    public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String get() {
        return preferences.getString(key, defaultValue);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }
}
