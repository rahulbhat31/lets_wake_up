package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionsActivity extends AppCompatActivity {


    Button submitBtn;
    Button nextBtn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String responseText;
    ImageView rightAns;
    ImageView wrongAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        submitBtn = (Button) findViewById(R.id.submitBtnID);
        nextBtn = (Button) findViewById(R.id.nextBtnID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                responseText = radioButton.getText().toString();

                if(responseText.equals("Carbon dioxide"))
                {
                    wrongAns = (ImageView) findViewById(R.id.wrongAnswerImgID);
                    wrongAns.setVisibility(View.GONE);
                    rightAns = (ImageView) findViewById(R.id.rightAnswerImgID);
                    rightAns.setVisibility(View.VISIBLE);
                }
                else
                {
                    rightAns = (ImageView) findViewById(R.id.rightAnswerImgID);
                    rightAns.setVisibility(View.GONE);
                    wrongAns = (ImageView) findViewById(R.id.wrongAnswerImgID);
                    wrongAns.setVisibility(View.VISIBLE);
                }

            }
        });



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsActivity.this, GWGameSimulation1.class);
                startActivity(intent);
            }
        });

    }
}
