package com.princhu;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import roboguice.config.AbstractAndroidModule;

public class PrinchuGuiceModule extends AbstractAndroidModule {

    public PrinchuGuiceModule() {
    }

    @Override
    protected void configure() {
        // if we want to do dependency injection with our own stuff, we set it up here

    }
}
