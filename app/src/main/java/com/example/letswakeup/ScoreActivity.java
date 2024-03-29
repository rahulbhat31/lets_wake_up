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

    int progressScore;
    ImageView home;
    Button section_score;
    TextView name;
    TextView ttlPoints;
    ImageView badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        badge = findViewById(R.id.badgeImg);
        name = findViewById(R.id.userName);
        ttlPoints = findViewById(R.id.pointsID);
        SharedPreferences sfu = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sfu.getString("username","");
        name.setText(userName);
        SharedPreferences sf = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        int env_score = sf.getInt(getString(R.string.env_score),0);
        int gw_score = sf.getInt(getString(R.string.global_warming_score),0);
        int wc_score = sf.getInt(getString(R.string.water_crisis_score),0);
        int wp_score = sf.getInt(getString(R.string.water_pollution_score),0);

        progressScore = env_score + gw_score+ wc_score+ wp_score;

        ttlPoints.setText(Integer.toString(progressScore));


        int total_s = sf.getInt(getString(R.string.total_score), 0);


        if(total_s >= 100){
            badge.setImageResource(R.drawable.star1);
        }
        if(total_s >= 200){
            badge.setImageResource(R.drawable.star2);
        }
        if(total_s >= 300){
            badge.setImageResource(R.drawable.star3);
        }
        if(total_s >= 400){
            badge.setImageResource(R.drawable.star4);
        }


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
