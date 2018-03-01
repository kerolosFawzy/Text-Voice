package com.massive.voicetext.clipboardcode;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.massive.voicetext.Ui.TextToVoiceActivity;

public class ClipBoardMain {
    public String setClipBoard(final Context context) {
        final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        final CharSequence[] copiedText = new CharSequence[1];
        assert clipboard != null;
        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                ClipData clipData = clipboard.getPrimaryClip();
                copiedText[0] = clipData.getItemAt(0).getText();
                Intent intent= new Intent(context, TextToVoiceActivity.class);
                intent.putExtra("copiedText",copiedText[0].toString() );
                context.startActivity(intent);
            }
        });

        return copiedText[0].toString();
    }


}
