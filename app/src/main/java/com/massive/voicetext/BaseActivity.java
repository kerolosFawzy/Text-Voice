package com.massive.voicetext;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setLogo(R.drawable.microphonelogo);
//            getSupportActionBar().setDisplayUseLogoEnabled(true);
//            getSupportActionBar().setTitle(R.string.app_name);
//        } catch (NullPointerException e) {
//            Log.e("ActionBar", e.getMessage());
//        }
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
                Intent intent= new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.AppBarNavigator:
                Intent intent2= new Intent(this, TextToVoiceActivity.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}

