package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WaterCrisis extends AppCompatActivity {

    ImageView tap;
    ImageView shower;
    TextView gameScoreTxt;
    int gameScore =0 ;
    int totalScore = 2;

    int currentSectionScore;
    int currentTotalScore;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sPreference;
    String questionType;
    int totalAquiredScore;
    TextView gwTutorialTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_crisis);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        questionType = getIntent().getExtras().getString(getString(R.string.question_type));

        tap = (ImageView) findViewById(R.id.runningTap);
        shower = (ImageView) findViewById(R.id.runningShower);
        gameScoreTxt = (TextView) findViewById(R.id.waterPollGSID);
        gwTutorialTxt = (TextView) findViewById(R.id.tutGWGameTxt);

        shower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScoreOnScreen();
                totalAquiredScore+=1;
                if(totalAquiredScore == 2)
                {
                    increaseScore();
                    setSectionCompleted();
                    Intent intent = new Intent(WaterCrisis.this, EndPage.class);
                    intent.putExtra(getString(R.string.question_type), questionType.toString());
                    startActivity(intent);
                }
                shower.setVisibility(View.INVISIBLE);
                gwTutorialTxt.setText("It's right!! you get one point Find Next");



            }
        });


        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScoreOnScreen();
                totalAquiredScore+=1;
                if(totalAquiredScore == 2)
                {
                    increaseScore();
                    setSectionCompleted();
                    Intent intent = new Intent(WaterCrisis.this, EndPage.class);
                    intent.putExtra(getString(R.string.question_type), questionType.toString());
                    startActivity(intent);
                }
                tap.setVisibility(View.INVISIBLE);
                gwTutorialTxt.setText("It's right!! you get one point Find Next");


            }
        });

    }

    private void increaseScore()
    {
        SharedPreferences.Editor sEditor = sPreference.edit();
        if(questionType.equals(getString(R.string.environment))){
            currentSectionScore = sPreference.getInt(getString(R.string.env_score), 0)+40;
            sEditor.putInt(getString(R.string.env_score), currentSectionScore);
        }
        else if(questionType.equals(getString(R.string.water_pollution))){
            currentSectionScore = sPreference.getInt(getString(R.string.water_pollution_score), 0)+40;
            sEditor.putInt(getString(R.string.water_pollution_score), currentSectionScore);
        }
        else if(questionType.equals(getString(R.string.global_warming))){
            currentSectionScore = sPreference.getInt(getString(R.string.global_warming_score), 0)+40;
            sEditor.putInt(getString(R.string.global_warming_score), currentSectionScore);
        }
        else if(questionType.equals(getString(R.string.water_crisis))) {
            currentSectionScore = sPreference.getInt(getString(R.string.water_crisis_score), 0)+40;
            sEditor.putInt(getString(R.string.water_crisis_score), currentSectionScore);
        }
        currentTotalScore = sPreference.getInt(getString(R.string.total_score), 0)+40;
        sEditor.putInt(getString(R.string.total_score), currentTotalScore);
        sEditor.apply();
    }


    private void updateScoreOnScreen(){
        gameScore = gameScore +1;
        String gameScoreStr = gameScore + "/ " +totalScore;
        gameScoreTxt.setText(gameScoreStr);
    }

    private void setSectionCompleted(){
        SharedPreferences.Editor sEditor = sPreference.edit();

        if(questionType.equals(getString(R.string.environment))){
            sEditor.putInt(getString(R.string.env_section_completed_flag), 1);
        }
        else if(questionType.equals(getString(R.string.water_pollution))){
            sEditor.putInt(getString(R.string.wp_section_completed_flag), 1);
        }
        else if(questionType.equals(getString(R.string.global_warming))){
            sEditor.putInt(getString(R.string.gw_section_completed_flag), 1);
        }
        else if(questionType.equals(getString(R.string.water_crisis))) {
            sEditor.putInt(getString(R.string.wc_section_completed_flag), 1);
        }

        sEditor.commit();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WaterCrisis.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}

