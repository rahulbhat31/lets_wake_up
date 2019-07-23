package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ScoreActivity extends AppCompatActivity {

    ProgressBar pbar;
    int progressScore;
    ImageView home;
    Button section_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        pbar = (ProgressBar)findViewById(R.id.pb);

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
