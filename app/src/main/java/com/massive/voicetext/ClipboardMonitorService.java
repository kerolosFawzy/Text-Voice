package com.massive.voicetext;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ClipboardMonitorService extends Service {
    private static final String TAG = "ClipboardManager";
    CharSequence[] copiedText = new CharSequence[1];
    private ClipboardManager clipboard;

    @Override
    public void onCreate() {
        super.onCreate();

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        assert clipboard != null;
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipData clipData = clipboard.getPrimaryClip();
                copiedText[0] = clipData.getItemAt(0).getText();
                ClipData.newPlainText("Copyied", copiedText[0]);
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(getBaseContext(), "You Can Read These words In Voice$Text App", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public IBinder onBind(Intent intent) {
//        intent = new Intent(this, TextToVoiceActivity.class);
//        intent.putExtra("copiedText", copiedText[0].toString());
//        startActivity(intent);
        return null;
    }


}
