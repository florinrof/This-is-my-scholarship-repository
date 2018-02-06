package com.example.android.proiect1;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity4 extends AppCompatActivity implements TextToSpeech.OnInitListener{
    public static int scoreValue3;
    public static boolean rightAnswer3;
    public static boolean rightAnswer4;
    private CheckBox chkBoxAns1;
    private CheckBox chkBoxAns2;
    private CheckBox chkBoxAns3;
    private CheckBox chkBoxAns4;
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initialUISetup1();
        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakQuestion3);
        txtText = getString(R.string.question_3);


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

    public void initialUISetup1()
    {
        chkBoxAns1 = (CheckBox) findViewById(R.id.checkbox_answer_question_3_1);
        chkBoxAns2 = (CheckBox) findViewById(R.id.checkbox_answer_question_3_2);
        chkBoxAns3 = (CheckBox) findViewById(R.id.checkbox_answer_question_3_3);
        chkBoxAns4 = (CheckBox) findViewById(R.id.checkbox_answer_question_3_4);
    }

    public void getClick1(View v)
    {scoreValue3=MainActivity3.scoreValue2;
        String strMessage = "";

        if(chkBoxAns1.isChecked())
        {
            strMessage+=getString(R.string.answerQuestion3_1)+",";
        }

        if(chkBoxAns2.isChecked())
        {
            strMessage+=getString(R.string.answerQuestion3_2)+",";
            scoreValue3=scoreValue3+5;

        }

        if(chkBoxAns3.isChecked())
        {
            strMessage+=getString(R.string.answerQuestion3_3)+",";
            scoreValue3=scoreValue3+5;


        }
        if(chkBoxAns4.isChecked())
        {
            strMessage+=getString(R.string.answerQuestion3_4);

        }

        showTextNotification1(strMessage);
    }

    public void showTextNotification1(String msgToDisplay)
    {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }
    public void preview3(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity3.class));
    }

    public void next4 (View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity5.class));
    }

}
