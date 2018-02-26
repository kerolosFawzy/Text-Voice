package com.massive.voicetext.clipboardcode;


import android.content.ClipboardManager;
import android.content.Context;

public class ClipBoardMain {
    public void setClipBoard(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        ClipboardManager.OnPrimaryClipChangedListener mPrimaryChangeListener = new ClipboardManager.OnPrimaryClipChangedListener() {
            public void onPrimaryClipChanged() {

                // this will be called whenever you copy something to the clipboard
            }
        };

        clipboard.addPrimaryClipChangedListener(mPrimaryChangeListener);
    }
}
