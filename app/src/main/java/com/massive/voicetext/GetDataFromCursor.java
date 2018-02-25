package com.massive.voicetext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

/**
 * Created by minafaw on 2/25/2018.
 */

public class GetDataFromCursor implements GetDataFromCursorInterface{
    @Override
    public ArrayList<TextModel> GetData(Context context) {
        @SuppressLint("Recycle") Cursor mCursor = context.getContentResolver().query(Constant.Entry.FULL_URI, null, null, null, null);
        ArrayList<TextModel> TextArray = new ArrayList<TextModel>();
        assert mCursor != null;
        while (mCursor.moveToNext()) {
            TextModel textModel = new TextModel();
            textModel.setID(mCursor.getString(mCursor.getColumnIndex("ID")));
            textModel.setText(mCursor.getString(mCursor.getColumnIndex("text")));

            TextArray.add(textModel);

        }
        return TextArray;
    }
}
