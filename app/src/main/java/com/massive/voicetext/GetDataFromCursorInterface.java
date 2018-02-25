package com.massive.voicetext;

import android.content.Context;

import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

/**
 * Created by minafaw on 2/25/2018.
 */

public interface GetDataFromCursorInterface {
    public ArrayList<TextModel> GetData(Context context);
}
