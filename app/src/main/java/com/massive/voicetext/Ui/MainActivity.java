package com.massive.voicetext.Ui;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.massive.javautli.GenerateId;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;
import com.massive.voicetext.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.RecorderImageButton)
    ImageButton RecorderImageButton;
    @BindView(R.id.ReturnedText)
    TextView ReturnedText;
    @BindString(R.string.recognizeLanguage)
    String Language;
    @BindView(R.id.MaterialFavoriteButton)
    MaterialFavoriteButton favoriteButton;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    Context context = this;
    static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecorderImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initIntent();
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String fontSize = sharedPreferences.getString(getString(R.string.font_size_input), "");
        if (!fontSize.isEmpty()) {
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            if (!pattern.matcher(fontSize).find()) {
                ReturnedText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) Float.parseFloat(fontSize));
            }
        }

        favoriteButton.setVisibility(View.GONE);

    }

    private void ShowFavButtn(){
        favoriteButton.setVisibility(View.VISIBLE);
        MaterialFavoriteButton favorite = new MaterialFavoriteButton.Builder(this).create();
        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                String returnedString = null;
                try {
                    returnedString = ReturnedText.getText().toString();
                } catch (Exception e) {
                }
                assert returnedString != null;
                if (!(returnedString.isEmpty())) {
                    if (favorite) {
                        id = GenerateId.idGenerator(returnedString);
                        ContentValues contentValues = addData(id, returnedString);
                        Uri uri = context.getContentResolver().insert(Constant.Entry.FULL_URI, contentValues);
                    } else {
                        Uri uri = Constant.Entry.FULL_URI;
                        uri = uri.buildUpon().appendPath(id).build();
                        context.getContentResolver().delete(uri, "Id =" + id, null);
                    }
                }
            }

        });
    }

//    private void getDataFromCursor() {
//        @SuppressLint("Recycle") Cursor mCursor = context.getContentResolver().query(Constant.Entry.FULL_URI, null, null, null, null);
//        while (mCursor.moveToNext())
//            Log.i("Cursor", mCursor.getString(mCursor.getColumnIndex("text")));
//    }


    private void initIntent() {
        Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, Language);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        recognizerIntent.putExtra("android.speech.extra.DICTATION_MODE", true);
        startActivityForResult(recognizerIntent, REQ_CODE_SPEECH_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ReturnedText.setText(result.get(0));
                    ShowFavButtn();
                }
                break;
            }
        }
    }

}