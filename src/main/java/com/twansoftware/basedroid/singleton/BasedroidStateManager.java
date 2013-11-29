package com.twansoftware.basedroid.singleton;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.twansoftware.basedroid.entity.Animal;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author: achuinard
 * 4/5/12
 */
public class BasedroidStateManager {
    private static final String DEFAULT_NULL_STRING = "";
    private static final int DEFAULT_NULL_INTEGER = -999;
    private static final Gson gson = new Gson();

    private SharedPreferences sharedPreferences;

    public BasedroidStateManager(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private void saveString(final String key, final String value) {
        setStringPreference(key, value);
    }

    private boolean setStringPreference(final String key, final String value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    private String loadString(final String key) {
        return sharedPreferences.getString(key, DEFAULT_NULL_STRING);
    }

    private void saveInteger(final String key, final Integer value) {
        setIntegerPreference(key, value);
    }

    private Integer loadInteger(final String key) {
        return sharedPreferences.getInt(key, DEFAULT_NULL_INTEGER);
    }

    private boolean setIntegerPreference(final String key, final Integer value) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    private void saveObject(final String key, final Serializable object) {
        final String json = gson.toJson(object);
        saveString(key, json);
    }

    private Object loadObject(final String key, final Class clazz) {
        final String json = loadString(key);
        return gson.fromJson(json, clazz);
    }

    private static final String RANDOM_UUID_KEY = "random_uuid";

    public void saveRandomUuid(final UUID uuid) {
        saveString(RANDOM_UUID_KEY, uuid.toString());
    }

    public UUID getSavedRandomUuid() {
        return UUID.fromString(loadString(RANDOM_UUID_KEY));
    }

    private static final String INTEGER_KEY = "integer_key";

    public void saveStupidInteger(final int toSave) {
        setIntegerPreference(INTEGER_KEY, toSave);
    }

    public Integer getSavedStupidInteger() {
        return loadInteger(INTEGER_KEY);
    }

    private static final String ANIMAL_KEY = "important_pojo";

    public void saveAnimal(final Animal animal) {
        saveObject(ANIMAL_KEY, animal);
    }

    public Animal getSavedImportantPojo() {
        return (Animal) loadObject(ANIMAL_KEY, Animal.class);
    }
}
