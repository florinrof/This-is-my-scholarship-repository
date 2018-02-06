
package com.example.android.proiect1;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;



public class MainActivity extends AppCompatActivity implements  TextToSpeech.OnInitListener {

   public static String name; //keep the score throw the activities
   EditText textFromEdiText;
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakName);
        txtText=getString(R.string.name_edit) ;


        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }

    @Override
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


    public void next1 (View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
        textFromEdiText = (EditText) findViewById(R.id.input_name);
        name = textFromEdiText.getText().toString();
    }
}