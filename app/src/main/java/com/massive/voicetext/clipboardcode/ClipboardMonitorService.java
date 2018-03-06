package com.massive.voicetext.clipboardcode;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ClipboardMonitorService extends Service {
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
            }
        });

    }


    public IBinder onBind(Intent intent) {
        return null;
    }


}
