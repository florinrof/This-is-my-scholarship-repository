package com.example.android.proiect1;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    public static int scoreValue2;
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;
    private RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakQuestion2);
        txtText = getString(R.string.question_2);


        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });



        /* Initialize Radio Group and attach click handler */
        radioGroup1 = (RadioGroup) findViewById(R.id.radio_group_question2);
        radioGroup1.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                scoreValue2 = MainActivity2.scoreValue;
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity3.this, rb.getText(), Toast.LENGTH_SHORT).show();


                }
//check if is the correct answer the increase the score whith 10 points and assign to string value=true
                if (rb.getText() == getString(R.string.answerQuestion2_2)) {
                    scoreValue2 = scoreValue2 + 10;

                }


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

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup1.clearCheck();
    }

    public void onSubmit1(View v) {
        RadioButton rb = (RadioButton) radioGroup1.findViewById(radioGroup1.getCheckedRadioButtonId());
        Toast.makeText(MainActivity3.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void preview2(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
    }

    public void next3(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity4.class));
    }

}
