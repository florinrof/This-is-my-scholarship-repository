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
import android.widget.Toast;

import java.util.Locale;

public class MainActivity5 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private RadioGroup radioGroup2;
    public static int scoreValue4;
    private TextToSpeech tts;
    private Button buttonSpeak;
    private String txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.btnSpeakQuestion4);
        txtText = getString(R.string.question_4);


        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });

          /* Initialize Radio Group and attach click handler */
        radioGroup2 = (RadioGroup) findViewById(R.id.radio_group_question4);
        radioGroup2.clearCheck();

        /* Attach CheckedChangeListener to radio group */
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                scoreValue4 = MainActivity4.scoreValue3;
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity5.this, rb.getText(), Toast.LENGTH_SHORT).show();


                }

                if (rb.getText() == getString(R.string.answerQuestion4_2)) {
                    scoreValue4 = scoreValue4 + 10;

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
        radioGroup2.clearCheck();
    }

    public void onSubmit2(View v) {
        RadioButton rb = (RadioButton) radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
        Toast.makeText(MainActivity5.this, rb.getText(), Toast.LENGTH_SHORT).show();

    }

    public void next5(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity6.class));
    }

    public void preview4(View view) {
        tts.shutdown();
        startActivity(new Intent(getApplicationContext(), MainActivity4.class));
    }
}
