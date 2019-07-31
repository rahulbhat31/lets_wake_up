package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    TextView nameView;
    Button env ;
    Button global_warmning;
    Button water_pollution;
    Button water_crisis;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);


        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sf.getString("username","");
        String gender = sf.getString("gender","");


        nameView = findViewById(R.id.name);
        nameView.setText(userName);

        profile = findViewById(R.id.avatar);
        if(gender.toLowerCase().equals("male")){
            profile.setImageResource(R.drawable.iconfinder_7_2694141);
        }
        else {
            profile.setImageResource(R.drawable.iconfinder_11_2694133);
        }


        env = findViewById(R.id.env);
        global_warmning = findViewById(R.id.global_warming);
        water_pollution = findViewById(R.id.water_pollu);
        water_crisis = findViewById(R.id.water_cri);


        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, TopicVideoPage.class);
                intent.putExtra("topic" ,getString(R.string.environment));
                startActivity(intent);
            }
        });
        global_warmning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, TopicVideoPage.class);
                intent.putExtra("topic", getString(R.string.global_warming));
                startActivity(intent);
            }
        });
        water_pollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, TopicVideoPage.class);
                intent.putExtra("topic", getString(R.string.water_pollution));
                startActivity(intent);
            }
        });
        water_crisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, TopicVideoPage.class);
                intent.putExtra("topic", getString(R.string.water_crisis));
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, profile.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
