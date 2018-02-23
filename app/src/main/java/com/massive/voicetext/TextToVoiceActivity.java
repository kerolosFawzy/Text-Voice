package com.massive.voicetext;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                }
                startActivity(intent2);
                break;
        }
        return true;
    }
}
