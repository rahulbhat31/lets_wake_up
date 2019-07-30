package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SectionsScores extends AppCompatActivity {

    ProgressBar env;
    ProgressBar glo_war;
    ProgressBar water_cr;
    ProgressBar water_poll;
    TextView envscore;
    TextView gwscore;
    TextView wcscore;
    TextView wpscore;
    ImageView home;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_scores);
        name = findViewById(R.id.userName);
        SharedPreferences sfu = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sfu.getString("username","");
        name.setText(userName);

        SharedPreferences sf = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        env = findViewById(R.id.enviornment);
        envscore = findViewById(R.id.envscore);
        int env_score = sf.getInt(getString(R.string.env_score),0);
        env.setProgress(env_score);
        envscore.setText(String.valueOf(env_score) + "/100");

        glo_war = findViewById(R.id.global_warming);
        gwscore = findViewById(R.id.gwscore);
        int gw_score = sf.getInt(getString(R.string.global_warming_score),0);
        glo_war.setProgress(gw_score);
        gwscore.setText(String.valueOf(gw_score) + "/100");

        water_cr = findViewById(R.id.water_crisis);
        wcscore = findViewById(R.id.wcscore);
        int wc_score = sf.getInt(getString(R.string.water_crisis_score),0);
        water_cr.setProgress(wc_score);
        wcscore.setText(String.valueOf(wc_score) + "/100");

        water_poll = findViewById(R.id.water_pollution);
        wpscore = findViewById(R.id.wpscore);
        int wp_score = sf.getInt(getString(R.string.water_pollution_score),0);
        water_poll.setProgress(wp_score);
        wpscore.setText(String.valueOf(wp_score) + "/100");

        home = (ImageView)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionsScores.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}
