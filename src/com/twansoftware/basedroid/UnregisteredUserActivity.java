package com.twansoftware.basedroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.util.Ln;

public class UnregisteredUserActivity extends RoboActivity {

    @Inject
    private SharedPreferences sharedPreferences;

    @Inject
    private PrinchuClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText(String.valueOf(sharedPreferences.getBoolean("test", false)));

        setContentView(view);

        Ln.v("Sample GET: %s", client.exampleJsonSearch());
    }
}
