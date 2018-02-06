package com.example.android.proiect1;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity6 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static int scoreValue5;

    private CheckBox chkBoxBicycle;
    private CheckBox chkBoxTv;
    private CheckBox chkBoxTable;
    private CheckBox chkBoxIron;
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        initialUISetup();
        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakQuestion5);
        txtText = getString(R.string.question_5);


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


    public void initialUISetup() {
        chkBoxBicycle = (CheckBox) findViewById(R.id.checkbox_answer_question_5_1);
        chkBoxTv = (CheckBox) findViewById(R.id.checkbox_answer_question_5_2);
        chkBoxTable = (CheckBox) findViewById(R.id.checkbox_answer_question_5_3);
        chkBoxIron = (CheckBox) findViewById(R.id.checkbox_answer_question_5_4);
    }

    public void getClick2(View v) {
        scoreValue5 = MainActivity5.scoreValue4;
        String strMessage = "";

        if (chkBoxBicycle.isChecked()) {
            strMessage += getString(R.string.answerquestion5_1) + ",";
        }

        if (chkBoxTv.isChecked()) {
            strMessage += getString(R.string.answerquestion5_2) + ",";
            scoreValue5 = scoreValue5 + 5;
        }

        if (chkBoxTable.isChecked()) {
            strMessage += getString(R.string.answerquestion5_3) + ",";

        }
        if (chkBoxIron.isChecked()) {
            strMessage += getString(R.string.answerquestion5_4);
            scoreValue5 = scoreValue5 + 5;
        }

        showTextNotification(strMessage);
    }

    public void showTextNotification(String msgToDisplay) {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }

    public void preview5(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity5.class));
    }

    public void finish(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity7.class));
    }
}
