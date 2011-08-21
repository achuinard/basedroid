package com.twansoftware.basedroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.inject.Inject;
import com.twansoftware.R;
import roboguice.activity.RoboActivity;

public class MainActivity extends RoboActivity
{
    @Inject
    private SharedPreferences sharedPreferences;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // testing shared prefs
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("test", true);
        editor.commit();
        startActivity(new Intent(this, UnregisteredUserActivity.class));
    }
}
