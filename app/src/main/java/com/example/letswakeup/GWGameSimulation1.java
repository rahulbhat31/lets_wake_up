package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GWGameSimulation1 extends AppCompatActivity {


    ImageView plasticBottleImg;
    ImageView plasticBagImg;
    TextView gameScoreTxt;
    int gameScore =0 ;
    int totalScore = 2;

    int currentSectionScore;
    int currentTotalScore;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sPreference;
    String questionType;
    int totalAquiredScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwgame_simulation1);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        questionType = getIntent().getExtras().getString(getString(R.string.question_type));

        plasticBottleImg = (ImageView) findViewById(R.id.bottleImgID);
        plasticBagImg = (ImageView) findViewById(R.id.plasticBagImgID);
        gameScoreTxt = (TextView) findViewById(R.id.waterPollGSID);

        plasticBagImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScoreOnScreen();
                totalAquiredScore+=1;
                if(totalAquiredScore == 2)
                {
                    increaseScore();
                    setSectionCompleted();
                    Intent intent = new Intent(GWGameSimulation1.this, EndPage.class);
                    intent.putExtra(getString(R.string.question_type), questionType.toString());
                    startActivity(intent);
                }
                plasticBagImg.setVisibility(View.INVISIBLE);


            }
        });


        plasticBottleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScoreOnScreen();
                totalAquiredScore+=1;
                if(totalAquiredScore == 2)
                {
                    increaseScore();
                }
                plasticBottleImg.setVisibility(View.INVISIBLE);

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
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GWGameSimulation1.this, HomePage.class);
        startActivity(intent);
        finish();
    }

}
