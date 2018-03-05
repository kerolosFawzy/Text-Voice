package com.massive.voicetext.Ui;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.massive.voicetext.LinearAdapter;
import com.massive.voicetext.R;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;
import com.massive.voicetext.mcontentProvider.GetDataFromCursor;
import com.massive.voicetext.mcontentProvider.GetDataFromCursorInterface;
import com.massive.voicetext.models.TextModel;

import java.util.ArrayList;

public class FavouritActivity extends BaseActivity implements LoaderManager.LoaderCallbacks {
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
        ArrayList<TextModel> TextArray = getData.GetData(context);
        if (!TextArray.isEmpty()) {
            LinearAdapter adapter = new LinearAdapter(context, TextArray);
            mRecyclerView.setAdapter(adapter);
        }
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this, Constant.Entry.FULL_URI, null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
