package com.massive.voicetext.Ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.massive.voicetext.R;
import com.massive.voicetext.activities.BaseActivity;
import com.massive.voicetext.activities.SettingsActivity;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextToVoiceActivity extends BaseActivity implements TextToSpeech.OnInitListener {
    @BindView(R.id.SpeakButton)
    Button SpeakButton;
    @BindView(R.id.InputText)
    EditText InputText;
    private TextToSpeech mTextToSpeech;
    private int MY_DATA_CHECK_CODE = 0;
    Menu menu;
    MenuItem mMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_voice);
        ButterKnife.bind(this);

        Intent TextToSpeakIntent = new Intent();
        TextToSpeakIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(TextToSpeakIntent, MY_DATA_CHECK_CODE);

        try {
            mMenuItem = menu.findItem(R.id.AppBarNavigator);
            mMenuItem.setTitle("Convert Speach To Text");
        } catch (NullPointerException E) {
        }
        SpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String InputString = InputText.getText().toString();
                speakWords(InputString);
            }
        });

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";

        assert clipboard != null;
        if ((clipboard.hasPrimaryClip())) {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            pasteData = item.getText().toString();
            InputText.setText(pasteData);
        }

    }

    private void speakWords(String speech) {
        mTextToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                mTextToSpeech = new TextToSpeech(this, this);
            } else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTextToSpeech.setLanguage(Locale.US);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.AppBarNavigator).setTitle("Convert Speach To Text");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AppbarSettings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.AppBarNavigator:
                Intent intent2 = new Intent(this, MainActivity.class);
                try {
                    mMenuItem.setTitle(getString(R.string.navigatorName));
                } catch (NullPointerException e) {
                    Log.e("Menu item", e.getMessage());
                }
                startActivity(intent2);
                break;
            case R.id.AppbarFav:
                Intent intent3 = new Intent(this, FavouritActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}
