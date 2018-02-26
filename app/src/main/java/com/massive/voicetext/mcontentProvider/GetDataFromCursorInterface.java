package com.massive.voicetext.mcontentProvider;

import android.content.Context;

import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

public interface GetDataFromCursorInterface {
    public ArrayList<TextModel> GetData(Context context);
}
