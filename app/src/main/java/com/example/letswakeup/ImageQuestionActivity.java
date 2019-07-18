package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class ImageQuestionActivity extends AppCompatActivity {

    private RadioGroup mFirstGroup;
    private RadioGroup mSecondGroup;

    ImageView rightAns;
    ImageView wrongAns;

    private boolean isChecking = true;
    private int mCheckedId;

    Button submitImgAnsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_question);

        mFirstGroup = (RadioGroup) findViewById(R.id.first_group);
        mSecondGroup = (RadioGroup) findViewById(R.id.second_group);

        submitImgAnsBtn = (Button) findViewById(R.id.imgQuesSubmitBtn);

        mFirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mSecondGroup.clearCheck();
                    mCheckedId = checkedId;
                }
                isChecking = true;
            }
        });

        mSecondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mFirstGroup.clearCheck();
                    mCheckedId = checkedId;
                }
                isChecking = true;
            }
        });

        submitImgAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCheckedId == R.id.imgQuesOption_4)
                {
                    wrongAns = (ImageView) findViewById(R.id.wrongAnswerImgQuesID);
                    wrongAns.setVisibility(View.GONE);
                    rightAns = (ImageView) findViewById(R.id.rightAnswerImgQuesID);
                    rightAns.setVisibility(View.VISIBLE);
                }
                else
                {
                    rightAns = (ImageView) findViewById(R.id.rightAnswerImgQuesID);
                    rightAns.setVisibility(View.GONE);
                    wrongAns = (ImageView) findViewById(R.id.wrongAnswerImgQuesID);
                    wrongAns.setVisibility(View.VISIBLE);
                }
            }
        });




    }
}
