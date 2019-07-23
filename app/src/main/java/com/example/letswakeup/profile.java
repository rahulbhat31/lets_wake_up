package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {


    Button score;
    TextView name;
    TextView dob;
    TextView email;
    TextView gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sf.getString("username","");
        name.setText(userName);

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
    }
}
