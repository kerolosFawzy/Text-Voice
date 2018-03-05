package com.massive.voicetext.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.massive.voicetext.R;
import com.massive.voicetext.Ui.FavouritActivity;
import com.massive.voicetext.Ui.TextToVoiceActivity;
import com.massive.voicetext.Utlis.Constant;

public class BaseActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        fireBase();


//        try {
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setLogo(R.drawable.microphonelogo);
//            getSupportActionBar().setDisplayUseLogoEnabled(true);
//            getSupportActionBar().setTitle(R.string.app_name);
//        } catch (NullPointerException e) {
//            Log.e("ActionBar", e.getMessage());
//        }
    }

//    public static void translateText(String sourceText, PrintStream out) {
//        Translate translate = TranslateOptions.getDefaultInstance().getService();
//
//        String text = "Hello, world!";
//
//        Translation translation = translate.translate(text, TranslateOption.sourceLanguage("en"), TranslateOption.targetLanguage("ru"));
//    }

    public void fireBase() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "voice");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AppbarSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.AppBarNavigator:
                Intent intent2 = new Intent(this, TextToVoiceActivity.class);
                startActivity(intent2);
                break;
            case R.id.AppbarFav:
                Intent intent3 = new Intent(this, FavouritActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }


    public static ContentValues addData(String id, String s) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.Entry.ID, id);
        contentValues.put(Constant.Entry.TEXT, s);
        return contentValues;
    }
}

