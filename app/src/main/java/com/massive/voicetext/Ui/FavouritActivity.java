package com.massive.voicetext.Ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massive.voicetext.LinearAdapter;
import com.massive.voicetext.R;
import com.massive.voicetext.models.TextModel;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;

import java.util.ArrayList;

public class FavouritActivity extends BaseActivity {
    Context context = this;
    ArrayList<TextModel> TextArray = new ArrayList<TextModel>();
    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourit);
        mRecyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        showData();
    }

    private void showData(){
        getDataFromCursor();
        if (!TextArray.isEmpty()) {
            LinearAdapter adapter = new LinearAdapter(context, TextArray);
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void getDataFromCursor() {
        @SuppressLint("Recycle") Cursor mCursor = context.getContentResolver().query(Constant.Entry.FULL_URI, null, null, null, null);
        assert mCursor != null;
        while (mCursor.moveToNext()) {
            TextModel textModel = new TextModel();
            textModel.setID(mCursor.getString(mCursor.getColumnIndex("ID")));
            textModel.setText(mCursor.getString(mCursor.getColumnIndex("text")));

            TextArray.add(textModel);

        }
    }

}
