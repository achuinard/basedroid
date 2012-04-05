package com.twansoftware.basedroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;

public class MainActivity extends RoboActivity {
  @Inject
  private SharedPreferences sharedPreferences;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    startActivity(new Intent(this, UnregisteredUserActivity.class));
  }
}
