package com.twansoftware.basedroid.singleton;

import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import roboguice.util.Ln;

/**
 * Author: achuinard
 * 4/5/12
 */
@Singleton
public class StateManager {
    private SharedPreferences sharedPreferences;

    @Inject
    public StateManager(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        Ln.d("Constructing StateManager...");
    }
}
