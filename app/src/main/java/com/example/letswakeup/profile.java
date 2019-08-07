package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {


    Button score;
    TextView name;
    TextView dob;
    TextView email;
    TextView gender;
    TextView p_userName;
    ImageView home;

    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView star4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        p_userName = findViewById(R.id.userName);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);

        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);


        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        //Log.d("environmentcompleted", String.valueOf(sf.getInt(getString(R.string.env_section_completed_flag), 0) == 1));


        SharedPreferences scorepref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        int total = scorepref.getInt(getString(R.string.total_score), 0);

        if(total >= 100){
            star1.setImageResource(R.drawable.star1);
        }
        if(total >= 200){
            star2.setImageResource(R.drawable.star2);
        }
        if(total >= 300){
            star3.setImageResource(R.drawable.star3);
        }
        if(total >= 400){
            star4.setImageResource(R.drawable.star4);
        }

        String userName = sf.getString("username","");
        name.setText(userName);
        p_userName.setText(userName);

        String emai = sf.getString("email","");
        email.setText(emai);

        String doba = sf.getString("dob","");
        dob.setText(doba);

        String gende = sf.getString("gender","");
        gender.setText(gende);


        score = findViewById(R.id.score);

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, ScoreActivity.class);
                startActivity(intent);
            }
        });

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, HomePage.class);
                startActivity(intent);
            }
        });



    }
}
