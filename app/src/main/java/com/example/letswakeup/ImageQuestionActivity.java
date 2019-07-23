package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ImageQuestionActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private RadioGroup mFirstGroup;
    private RadioGroup mSecondGroup;
    String clickedImageName;

    TextView rightAns;
    TextView wrongAns;
    ImageView profile;
    private boolean isChecking = true;
    private int mCheckedId;

    Button submitImgAnsBtn;

    Button nextBtn;

    public static final String MyPREFERENCES = "MyPrefs" ;

    ImageButton imgClicked;
    ImageButton prevImgClicked;

    TextView questionTextView;
    ImageButton option1Img;
    ImageButton option2Img;
    ImageButton option3Img;
    ImageButton option4Img;

    String questionStr;
    String[] quesArr;
    String answerImgName;

    String[] questionnaire;
    String questionType;

    int currentSectionScore;
    int currentTotalScore;

    SharedPreferences sPreference;

    int questionNumber;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 100;

    boolean canGoToNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_question);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);


        profile = (ImageView)findViewById(R.id.avatar);

       /* mFirstGroup = (RadioGroup) findViewById(R.id.first_group);
        mSecondGroup = (RadioGroup) findViewById(R.id.second_group);*/

        wrongAns = (TextView) findViewById(R.id.wrongImgAnsStr);
        rightAns = (TextView) findViewById(R.id.rightImgAnsStr);

        submitImgAnsBtn = (Button) findViewById(R.id.imgQuesSubmitBtn);
        nextBtn = (Button) findViewById(R.id.imgNextBtn);

        questionType = getIntent().getExtras().getString(getString(R.string.question_type));
        getQuestionnaireAndQuestion();



        /*mFirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        });*/

        submitImgAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickedImageName.equals(answerImgName))
                {
                    canGoToNext = true;
                    increaseScore();
                    setNextQuestionNumber();
                    wrongAns.setVisibility(View.GONE);
                    rightAns.setVisibility(View.VISIBLE);
                    submitImgAnsBtn.setVisibility(View.GONE);
                    nextBtn.setVisibility(View.VISIBLE);

                }
                else
                {
                    rightAns = (TextView) findViewById(R.id.rightImgAnsStr);
                    rightAns.setVisibility(View.GONE);
                    wrongAns = (TextView) findViewById(R.id.wrongImgAnsStr);
                    wrongAns.setVisibility(View.VISIBLE);
                }
            }
        });

        option1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImageClicked(view);
            }
        });

        option2Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImageClicked(view);
            }
        });

        option3Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImageClicked(view);
            }
        });

        option4Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImageClicked(view);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageQuestionActivity.this, profile.class);
                startActivity(intent);
            }
        });

        /*nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(questionNumber >2)
                {
                    Intent intent = new Intent(ImageQuestionActivity.this, GWGameSimulation1.class);
                    intent.putExtra(getString(R.string.question_type), questionType);
                    startActivity(intent);
                }
                else
                {
                    Drawable bgwhiteImg= ContextCompat.getDrawable(getApplicationContext(), R.drawable.whitebdg);
                    imgClicked.setBackground(bgwhiteImg);
                    imgClicked.setPressed(false);
                    getQuestionnaireAndQuestion();
                    submitImgAnsBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.GONE);
                    wrongAns.setVisibility(View.GONE);
                    rightAns.setVisibility(View.GONE);

                }

            }
        });
*/
    }

    private void setNextQuestionNumber(){
        SharedPreferences.Editor sEditor = sPreference.edit();
        questionNumber = questionNumber+1;
        if(questionType.equals(getString(R.string.environment))){
            sEditor.putInt(getString(R.string.img_env_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.water_pollution))){
            sEditor.putInt(getString(R.string.img_water_poll_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.global_warming))){
            sEditor.putInt(getString(R.string.img_global_warming_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.water_crisis))) {
            sEditor.putInt(getString(R.string.img_water_crisis_ques_no), questionNumber);
        }

        sEditor.apply();
    }

    private void setImageClicked(View view)
    {
        if(imgClicked != null)
        {
            prevImgClicked = imgClicked;
            Drawable bgwhiteImg= ContextCompat.getDrawable(this, R.drawable.whitebdg);
            prevImgClicked.setBackground(bgwhiteImg);
            prevImgClicked.setPressed(false);
        }
        imgClicked = (ImageButton) findViewById(view.getId());
        Drawable bgImg= ContextCompat.getDrawable(this, R.drawable.redbgd);
        imgClicked.setBackground(bgImg);
        clickedImageName = getResources().getResourceEntryName(imgClicked.getId());
        imgClicked.setPressed(true);
    }

    private void getQuestionnaireAndQuestion()
    {

        if(questionType.equals(getString(R.string.environment)))
        {
            questionnaire = getResources().getStringArray(R.array.img_env_question);
            questionNumber = sPreference.getInt(getString(R.string.img_env_ques_no),0);
        }
        else if(questionType.equals(getString(R.string.water_pollution)))
        {
            questionnaire = getResources().getStringArray(R.array.img_water_pollution_question);
            questionNumber = sPreference.getInt(getString(R.string.img_water_poll_ques_no), 0);
        }
        else if(questionType.equals(getString(R.string.global_warming)))
        {
            questionnaire = getResources().getStringArray(R.array.img_global_warming_question);
            questionNumber = sPreference.getInt(getString(R.string.img_global_warming_ques_no), 0);
        }
        else if(questionType.equals(getString(R.string.water_crisis)))
        {
            questionnaire = getResources().getStringArray(R.array.img_water_crisis_question);
            questionNumber = sPreference.getInt(getString(R.string.img_water_crisis_ques_no), 0);
        }
        questionStr = questionnaire[questionNumber];

        parseAndSetQuestion();
    }

    private void parseAndSetQuestion()
    {
        questionTextView = (TextView) findViewById(R.id.imgeTxtQuestionID);
        option1Img = (ImageButton) findViewById(R.id.img1);
        option2Img = (ImageButton) findViewById(R.id.img2);
        option3Img = (ImageButton) findViewById(R.id.img3);
        option4Img = (ImageButton) findViewById(R.id.img4);



        quesArr= questionStr.split(", ");
        questionTextView.setText(quesArr[0].trim());
        option1Img.setImageResource(getResources().getIdentifier(quesArr[1].trim(), "drawable",
                getApplicationContext().getPackageName()));

        option2Img.setImageResource(getResources().getIdentifier(quesArr[2].trim(), "drawable",
                getApplicationContext().getPackageName()) );

        option3Img.setImageResource(getResources().getIdentifier(quesArr[3].trim(), "drawable",
                getApplicationContext().getPackageName()) );

        option4Img.setImageResource(getResources().getIdentifier(quesArr[4].trim(), "drawable",
                getApplicationContext().getPackageName()) );


        answerImgName= quesArr[5].trim();

        wrongAns.setText(quesArr[6].trim());
    }


    private void increaseScore()
    {
        SharedPreferences.Editor sEditor = sPreference.edit();
        if(questionType.equals(R.string.environment)){
            currentSectionScore = sPreference.getInt(getString(R.string.env_score), 0)+10;
            sEditor.putInt(getString(R.string.env_score), currentSectionScore);
        }
        else if(questionType.equals(R.string.water_pollution)){
            currentSectionScore = sPreference.getInt(getString(R.string.water_pollution_score), 0)+10;
            sEditor.putInt(getString(R.string.water_pollution_score), currentSectionScore);
        }
        else if(questionType.equals(R.string.global_warming)){
            currentSectionScore = sPreference.getInt(getString(R.string.global_warming_score), 0)+10;
            sEditor.putInt(getString(R.string.global_warming_score), currentSectionScore);
        }
        else if(questionType.equals(R.string.water_crisis)) {
            currentSectionScore = sPreference.getInt(getString(R.string.water_crisis_score), 0)+10;
            sEditor.putInt(getString(R.string.water_crisis_score), currentSectionScore);
        }
        currentTotalScore = sPreference.getInt(getString(R.string.total_score), 0)+10;
        sEditor.putInt(getString(R.string.total_score), currentTotalScore);
        sEditor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ImageQuestionActivity.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(canGoToNext == true)
        {
            Sensor mySensor = sensorEvent.sensor;

            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        goToNextQuestion();
                    }

                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void goToNextQuestion(){
        canGoToNext = false;
        if(questionNumber >2)
        {
            Intent intent = new Intent(ImageQuestionActivity.this, GWGameSimulation1.class);
            intent.putExtra(getString(R.string.question_type), questionType);
            startActivity(intent);
        }
        else
        {
            Drawable bgwhiteImg= ContextCompat.getDrawable(getApplicationContext(), R.drawable.whitebdg);
            imgClicked.setBackground(bgwhiteImg);
            imgClicked.setPressed(false);
            getQuestionnaireAndQuestion();
            submitImgAnsBtn.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.GONE);
            wrongAns.setVisibility(View.GONE);
            rightAns.setVisibility(View.GONE);

        }
    }
}
