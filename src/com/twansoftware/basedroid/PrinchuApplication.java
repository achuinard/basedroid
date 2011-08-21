package com.twansoftware.basedroid;

import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import com.google.inject.Module;
import roboguice.application.RoboApplication;

import java.util.List;

public class PrinchuApplication extends RoboApplication {
    @Override
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new PrinchuGuiceModule());
    }
}
