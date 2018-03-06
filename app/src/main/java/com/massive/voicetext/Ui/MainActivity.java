package com.massive.voicetext.Ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.massive.javautli.GenerateId;
import com.massive.voicetext.R;
import com.massive.voicetext.Utlis.Constant;
import com.massive.voicetext.activities.BaseActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    static String id;
    @BindView(R.id.RecorderImageButton)
    ImageButton RecorderImageButton;
    @BindView(R.id.FavimageButton)
    ImageButton FavimageButton;
    @BindView(R.id.ReturnedText)
    TextView ReturnedText;
    @BindString(R.string.recognizeLanguage)
    String Language;
    @BindView(R.id.MaterialFavoriteButton)
    MaterialFavoriteButton favoriteButton;
    Context context = this;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        MobileAds.initialize(this, getString(R.string.banner_ad_unit_id));
        ButterKnife.bind(this);
        RecorderImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initIntent();
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String fontSize = sharedPreferences.getString(getString(R.string.font_size_input), "18");
        if (!fontSize.isEmpty()) {
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            if (!pattern.matcher(fontSize).find()) {
                ReturnedText.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) Float.parseFloat(fontSize));
            }
        }
        FavimageButton.setVisibility(View.GONE);
        favoriteButton.setVisibility(View.GONE);
    }

    private void ShowFavButtn() {
        favoriteButton.setVisibility(View.VISIBLE);
        MaterialFavoriteButton favorite = new MaterialFavoriteButton.Builder(this).create();
        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                String returnedString = null;
                try {
                    returnedString = ReturnedText.getText().toString();
                } catch (Exception ignored) {
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

    private void ShowFavImageButtn() {
        FavimageButton.setVisibility(View.VISIBLE);
        flag = true;
        FavimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnedString = null;
                try {
                    returnedString = ReturnedText.getText().toString();
                } catch (Exception ignored) {
                }
                assert returnedString != null;
                if (!returnedString.isEmpty()) {
                    if (flag) {
                        FavimageButton.setImageResource(R.drawable.fav);
                        id = GenerateId.idGenerator(returnedString);
                        ContentValues contentValues = addData(id, returnedString);
                        Uri uri = context.getContentResolver().insert(Constant.Entry.FULL_URI, contentValues);
                        flag = false;
                    } else {
                        FavimageButton.setImageResource(R.drawable.unfav);
                        Uri uri = Constant.Entry.FULL_URI;
                        uri = uri.buildUpon().appendPath(id).build();
                        context.getContentResolver().delete(uri, "Id =" + id, null);
                        flag = true;
                    }
                }
            }
        });
    }

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
        favoriteButton.setVisibility(View.GONE);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ReturnedText.setText(result.get(0));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ShowFavButtn();
                    } else {
                        ShowFavImageButtn();
                    }

                }
                break;
            }
        }
//        String s = ReturnedText.getText().toString();
//        if (!s.isEmpty()) {
//            GoogleTranslateApi translateApi;
//            translateApi = new GoogleTranslateApi(new NotifyChange() {
//                @Override
//                public void GetData(String s) {
//                    TranslatedString=s;
//                }
//            });
//            translateApi.execute(s);
//
////            String translated = GoogleTranslateApi.translateTextWithOptionsAndModel(s, "ar");
//            ReturnedText.setText(TranslatedString);
//        }
    }

}
