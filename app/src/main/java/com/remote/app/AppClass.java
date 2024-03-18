package com.remote.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class AppClass extends Application {

    /* renamed from: a  reason: collision with root package name */
    public static Context f202a;

    public void onCreate() {
        super.onCreate();
        f202a = getApplicationContext();
        startService(new Intent(this, MainService.class));
    }
}
