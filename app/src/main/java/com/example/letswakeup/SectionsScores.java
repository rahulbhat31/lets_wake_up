package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SectionsScores extends AppCompatActivity {

    ProgressBar env;
    ProgressBar glo_war;
    ProgressBar water_cr;
    ProgressBar water_poll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_scores);

        SharedPreferences sf = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        env = findViewById(R.id.enviornment);
        int env_score = sf.getInt(getString(R.string.env_score),0);
        env.setProgress(10);

        glo_war = findViewById(R.id.global_warming);
        int gw_score = sf.getInt(getString(R.string.global_warming_score),0);
        glo_war.setProgress(gw_score);

        water_cr = findViewById(R.id.water_crisis);
        int wc_score = sf.getInt(getString(R.string.water_crisis_score),0);
        water_cr.setProgress(wc_score);

        water_poll = findViewById(R.id.water_pollution);
        int wp_score = sf.getInt(getString(R.string.water_pollution_score),0);
        water_poll.setProgress(wp_score);

    }
}
