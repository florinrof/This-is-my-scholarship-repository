package com.example.android.proiect1;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity7 extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        int scoreValueFinal = MainActivity6.scoreValue5;
        displayScore(scoreValueFinal);
        String messageToShow = "";

        if (scoreValueFinal == 50) {
            messageToShow = "Great job,  "+MainActivity.name+ "!";
        } else {
            messageToShow = "Next time you will do better, "+ MainActivity.name + "!";
        }
        Toast.makeText(MainActivity7.this, messageToShow, Toast.LENGTH_LONG).show();
        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakFinal);
        txtText = messageToShow;


        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });

    }
    public void onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {
        tts.setPitch(1);
        tts.setSpeechRate(1);
        tts.speak(txtText, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void displayScore(int val3) {
        TextView score = (TextView) findViewById(R.id.text_score);
        score.setText("\n" + val3);
    }

    public void back (View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity6.class));
    }
}
