package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class ScoreActivity extends AppCompatActivity {

    ProgressBar pbar;
    int progressScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        pbar = (ProgressBar)findViewById(R.id.pb);
        progressScore = 50;
        pbar.setProgress(progressScore);
    }
}
