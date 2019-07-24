package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    ProgressBar pbar;
    int progressScore;
    ImageView home;
    Button section_score;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        pbar = (ProgressBar)findViewById(R.id.pb);
        name = findViewById(R.id.userName);
        SharedPreferences sfu = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sfu.getString("username","");
        name.setText(userName);
        SharedPreferences sf = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        int env_score = sf.getInt(getString(R.string.env_score),0);
        int gw_score = sf.getInt(getString(R.string.global_warming_score),0);
        int wc_score = sf.getInt(getString(R.string.water_crisis_score),0);
        int wp_score = sf.getInt(getString(R.string.water_pollution_score),0);
        progressScore = env_score + gw_score+ wc_score+ wp_score;

        pbar.setProgress(progressScore);

        home = (ImageView)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        section_score = findViewById(R.id.section_score);
        section_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, SectionsScores.class);
                startActivity(intent);
            }
        });
    }
}
