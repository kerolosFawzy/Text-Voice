package com.massive.voicetext;

import android.database.Cursor;
import android.os.AsyncTask;

import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;



public class TextAsyncTask extends AsyncTask<Cursor, Void, ArrayList<TextModel>> {

    @Override
    protected ArrayList<TextModel> doInBackground(Cursor... cursor) {
        ArrayList<TextModel> TextArray = new ArrayList<>();
        Cursor mycursor = cursor[0];

        while (mycursor.moveToNext()) {
            TextModel textModel = new TextModel();
            textModel.setID(mycursor.getString(mycursor.getColumnIndex("ID")));
            textModel.setText(mycursor.getString(mycursor.getColumnIndex("text")));
            TextArray.add(textModel);
        }
        return TextArray;
    }
}
