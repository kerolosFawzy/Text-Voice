package com.massive.voicetext.Ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massive.voicetext.GetDataFromCursor;
import com.massive.voicetext.GetDataFromCursorInterface;
import com.massive.voicetext.LinearAdapter;
import com.massive.voicetext.R;
import com.massive.voicetext.models.TextModel;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;

import java.util.ArrayList;

public class FavouritActivity extends BaseActivity {
    Context context = this;
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

    private void showData() {
        GetDataFromCursorInterface getData = new GetDataFromCursor();
        ArrayList<TextModel> TextArray = new ArrayList<>();
        TextArray = getData.GetData(context);
        if (!TextArray.isEmpty()) {
            LinearAdapter adapter = new LinearAdapter(context, TextArray);
            mRecyclerView.setAdapter(adapter);
        }
    }



}
