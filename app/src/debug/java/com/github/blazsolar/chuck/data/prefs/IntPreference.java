package com.github.blazsolar.chuck.data.prefs;

import android.content.SharedPreferences;

/**
 * Created by Blaz Solar on 22/10/14.
 */
public class IntPreference {

    private final SharedPreferences preferences;
    private final String key;
    private final int defaultValue;

    public IntPreference(String key, SharedPreferences preferences) {
        this(preferences, key, 0);
    }

    public IntPreference(SharedPreferences preferences, String key, int defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public int get() {
        return preferences.getInt(key, defaultValue);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }
}
