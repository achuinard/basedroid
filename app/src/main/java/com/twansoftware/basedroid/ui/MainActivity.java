package com.twansoftware.basedroid.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;

public class MainActivity extends RoboSherlockActivity {
  @Inject
  private SharedPreferences sharedPreferences;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    getSupportActionBar().setTitle("Custom Title Bar");
  }
}
