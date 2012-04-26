package com.twansoftware.basedroid.singleton;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import roboguice.util.Ln;

import java.io.Serializable;

/**
 * Author: achuinard
 * 4/5/12
 */
@Singleton
public class BasedroidStateManager {
    private static final String DEFAULT_NULL_STRING = "";
    private static final int DEFAULT_NULL_INTEGER = -999;
    private static final Gson gson = new Gson();

    private SharedPreferences sharedPreferences;

    @Inject
    public BasedroidStateManager(final SharedPreferences sharedPreferences) {
        Ln.d("Constructing BasedroidStateManager...");
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Saves a key + String value pair to the app.
     * @param key
     * @param value
     */
    public void saveString(final String key, final String value) {
        setStringPreference(key, value);
    }

    /**
     * Loads a String value from the app based on the key.
     * @param key Lookup key.
     * @return Value associated with key, otherwise DEFAULT_NULL_STRING
     */
    public String loadString(final String key) {
        return sharedPreferences.getString(key, DEFAULT_NULL_STRING);
    }

    /**
     * Saves a key + Integer value pair to the app.
     * @param key
     * @param value
     */
    public void saveInteger(final String key, final Integer value) {
        setIntegerPreference(key, value);
    }

    /**
     * Loads an Integer value from the app based on the key.
     * @param key Lookup key.
     * @return Value associated with key, otherwise DEFAULT_NULL_INTEGER
     */
    public Integer loadInteger(final String key) {
        return sharedPreferences.getInt(key, DEFAULT_NULL_INTEGER);
    }

    /**
     * Saves a serializable object.
     * @param key The key which to save it under.
     * @param object The object to save.
     */
    public void saveObject(final String key, final Serializable object) {
        final String json = gson.toJson(object);
        saveString(key, json);
    }

    /**
     * Loads a previously saved object.
     * @param key The key the object was saved under.
     * @param clazz The type of object being loaded.
     * @return The loaded object.
     */
    public Object loadObject(final String key, final Class clazz) {
        final String json = loadString(key);
        return gson.fromJson(json, clazz);
    }

    private boolean setIntegerPreference(final String key, final Integer value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    private boolean setStringPreference(final String key, final String value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }
}
