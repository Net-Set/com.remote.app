package com.remote.app;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class ServiceReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Class<MainService> cls = MainService.class;
        if (Build.VERSION.SDK_INT >= 26) {
            ComponentName unused = context.startForegroundService(new Intent(context, cls));
        } else {
            context.startService(new Intent(context, cls));
        }
    }
}
