package com.massive.voicetext.Ui;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massive.voicetext.LinearAdapter;
import com.massive.voicetext.R;
import com.massive.voicetext.TextAsyncTask;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

public class FavouritActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    Context context = this;
    RecyclerView mRecyclerView;
    String[] projection = new String[]{Constant.Entry.ID, Constant.Entry.TEXT};
    TextAsyncTask textAsyncTask;
    ArrayList<TextModel> TextArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourit);
        mRecyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        getLoaderManager().initLoader(1, null, this);
    }

//    private void showData() {
//        GetDataFromCursorInterface getData = new GetDataFromCursor();
//        ArrayList<TextModel> TextArray = getData.GetData(context);
//        if (!TextArray.isEmpty()) {
//            LinearAdapter adapter = new LinearAdapter(context, TextArray);
//            mRecyclerView.setAdapter(adapter);
//        }
//    }


    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Constant.Entry.FULL_URI, projection, null, null, null);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                TextModel textModel = new TextModel();
//                textModel.setID(cursor.getString(cursor.getColumnIndex("ID")));
//                textModel.setText(cursor.getString(cursor.getColumnIndex("text")));
//                TextArray.add(textModel);
//
//            }
            textAsyncTask = new TextAsyncTask() {
                @Override
                protected void onPostExecute(ArrayList<TextModel> textModels) {
                    TextArray = textModels;
                    if (!TextArray.isEmpty()) {
                        LinearAdapter adapter = new LinearAdapter(context, TextArray);
                        mRecyclerView.setAdapter(adapter);
                    }
                }
            };
            textAsyncTask.execute(cursor);

        }

    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
