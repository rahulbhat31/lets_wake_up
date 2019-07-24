package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InstructionsPage extends AppCompatActivity {


    Button play;
    String questionType;
    ImageView profile;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_page);
        questionType  = getIntent().getExtras().getString(getString(R.string.question_type));
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsPage.this, GWGameSimulation1.class);
                intent.putExtra(getString(R.string.question_type), questionType);
                startActivity(intent);
            }
        });


        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sf.getString("username","");
        String gender = sf.getString("gender","");


        profile = findViewById(R.id.avatar);
        if(gender.toLowerCase().equals("male")){
            profile.setImageResource(R.drawable.iconfinder_7_2694141);
        }
        else {
            profile.setImageResource(R.drawable.iconfinder_11_2694133);
        }

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsPage.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

}
