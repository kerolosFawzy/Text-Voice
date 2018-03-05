package com.massive.voicetext.mcontentProvider;

import android.content.Context;

import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

public interface GetDataFromCursorInterface {
    ArrayList<TextModel> GetData(Context context);
}
