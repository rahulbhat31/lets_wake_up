package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructionsPage extends AppCompatActivity {


    Button play;
    String questionType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_page);
        questionType  = getIntent().getExtras().getString(getString(R.string.question_type));
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructionsPage.this, ImageQuestionActivity.class);
                intent.putExtra(getString(R.string.question_type), questionType);
                startActivity(intent);
            }
        });
    }

}
