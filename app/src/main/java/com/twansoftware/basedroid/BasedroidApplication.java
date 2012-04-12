package com.twansoftware.basedroid;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;

public class BasedroidApplication extends Application {
    public BasedroidApplication() {

    }

    public BasedroidApplication(final Context context) {
        this();
        attachBaseContext(context);
    }

    public BasedroidApplication(final Instrumentation instrumentation) {
        this(instrumentation.getContext());
    }
}
