package com.twansoftware.basedroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.inject.Inject;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;
import roboguice.activity.RoboActivity;
import roboguice.util.Ln;

public class UnregisteredUserActivity extends RoboActivity {

  @Inject
  private SharedPreferences sharedPreferences;

  @Inject
  private BasedroidHttpClient client;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Ln.v("Sample GET: %s", client.exampleJsonSearch());
  }
}
