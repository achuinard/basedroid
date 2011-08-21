package com.twansoftware.basedroid;

import com.google.inject.Module;
import roboguice.application.RoboApplication;

import java.util.List;

public class BasedroidApplication extends RoboApplication {
    @Override
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new BasedroidGuiceModule());
    }
}
