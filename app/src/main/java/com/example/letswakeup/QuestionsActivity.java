package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 200;

    TextView tutQuestionPageText;

    TextView questionTxtView;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;
    RadioButton btn4;
    int questionNumber;
    String answer;
    TextView name;
    Button submitBtn;
    RadioGroup radioGroup;
    RadioButton selectedButton;
    String responseText;
    TextView rightAns;
    TextView wrongAns;
    ImageView profile;
    ImageView home;

    int currentSectionScore;
    int currentTotalScore;

    public static final String MyPREFERENCES = "MyPrefs" ;


    SharedPreferences sPreference;

    String[] questionnaire;
    String questionStr;
    String questionType;

    String[] quesArr;
    String wrongAnsResponse;

    boolean canGoToNext = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        questionType = getIntent().getExtras().getString(getString(R.string.question_type));

        tutQuestionPageText = (TextView) findViewById(R.id.tutorialQuestionPage);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);


        getQuestionnaireAndQuestion();

        profile = (ImageView)findViewById(R.id.avatar);

        name = findViewById(R.id.userName);
        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String userName = sf.getString("username","");
        name.setText(userName);
        String gender = sf.getString("gender","");


        profile = findViewById(R.id.avatar);
        if(gender.toLowerCase().equals("male")){
            profile.setImageResource(R.drawable.iconfinder_7_2694141);
        }
        else {
            profile.setImageResource(R.drawable.iconfinder_11_2694133);
        }
        submitBtn = (Button) findViewById(R.id.submitBtnID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);

        wrongAns = (TextView) findViewById(R.id.wrongAnsStr);
        rightAns = (TextView) findViewById(R.id.rightAnsStr);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == -1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one option", Toast.LENGTH_SHORT).show();
                    Log.d("QAOD", "No option selected for question");
                }
                else
                {
                    selectedButton = (RadioButton) findViewById(selectedId);
                    responseText = selectedButton.getText().toString();

                    if(responseText.equals(answer))
                    {
                        canGoToNext = true;
                        increaseScore();
                        setNextQuestionNumber();
                        wrongAns.setVisibility(View.GONE);
                        rightAns.setVisibility(View.VISIBLE);
                        submitBtn.setVisibility(View.GONE);
                        tutQuestionPageText.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        tutQuestionPageText.setVisibility(View.GONE);
                        rightAns = (TextView) findViewById(R.id.rightAnsStr);
                        rightAns.setVisibility(View.GONE);
                        wrongAns = (TextView) findViewById(R.id.wrongAnsStr);
                        wrongAns.setText(wrongAnsResponse);
                        wrongAns.setVisibility(View.VISIBLE);
                    }
                }



            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsActivity.this, profile.class);
                startActivity(intent);
            }
        });

    }

    private void getQuestionnaireAndQuestion()
    {

        if(questionType.equals(getString(R.string.environment)))
        {
            questionnaire = getResources().getStringArray(R.array.env_question);
            questionNumber = sPreference.getInt(getString(R.string.env_ques_no), 0);

        }
        else if(questionType.equals(getString(R.string.water_pollution)))
        {
            questionnaire = getResources().getStringArray(R.array.water_pollution_question);
            questionNumber = sPreference.getInt(getString(R.string.water_poll_ques_no), 0);

        }
        else if(questionType.equals(getString(R.string.global_warming)))
        {
            questionnaire = getResources().getStringArray(R.array.global_warming_question);
            questionNumber = sPreference.getInt(getString(R.string.global_warming_ques_no), 0);
        }
        else if(questionType.equals(getString(R.string.water_crisis)))
        {
            questionnaire = getResources().getStringArray(R.array.water_crisis_question);
            questionNumber = sPreference.getInt(getString(R.string.water_crisis_ques_no), 0);
        }
        questionStr = questionnaire[questionNumber];

        parseAndSetQuestion();
    }

    private void parseAndSetQuestion()
    {
        questionTxtView = (TextView) findViewById(R.id.textQuestionID);
        btn1 = (RadioButton) findViewById(R.id.option1ID);
        btn2 = (RadioButton) findViewById(R.id.option2ID);
        btn3 = (RadioButton) findViewById(R.id.option3ID);
        btn4 = (RadioButton) findViewById(R.id.option4ID);

        quesArr= questionStr.split(", ");
        questionTxtView.setText(quesArr[0].trim());
        btn1.setText(quesArr[1].trim());
        btn2.setText(quesArr[2].trim());
        btn3.setText(quesArr[3].trim());
        btn4.setText(quesArr[4].trim());

        answer = quesArr[5].trim();
        wrongAnsResponse = quesArr[6].trim();
    }

    private void setNextQuestionNumber(){
        SharedPreferences.Editor sEditor = sPreference.edit();
        questionNumber = questionNumber+1;
        if(questionType.equals(getString(R.string.environment))){
            sEditor.putInt(getString(R.string.env_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.water_pollution))){
            sEditor.putInt(getString(R.string.water_poll_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.global_warming))){
            sEditor.putInt(getString(R.string.global_warming_ques_no), questionNumber);
        }
        else if(questionType.equals(getString(R.string.water_crisis))) {
            sEditor.putInt(getString(R.string.water_crisis_ques_no), questionNumber);
        }
        sEditor.apply();
    }

    private void increaseScore()
    {
        SharedPreferences.Editor sEditor = sPreference.edit();
        if(questionType.equals(getString(R.string.environment))){
            currentSectionScore = sPreference.getInt(getString(R.string.env_score), 0)+10;
            sEditor.putInt(getString(R.string.env_score), currentSectionScore);

        }
        else if(questionType.equals(getString(R.string.water_pollution))){
            currentSectionScore = sPreference.getInt(getString(R.string.water_pollution_score), 0)+10;
            sEditor.putInt(getString(R.string.water_pollution_score), currentSectionScore);
        }
        else if(questionType.equals(getString(R.string.global_warming))){
            currentSectionScore = sPreference.getInt(getString(R.string.global_warming_score), 0)+10;
            sEditor.putInt(getString(R.string.global_warming_score), currentSectionScore);
        }
        else if(questionType.equals(getString(R.string.water_crisis))) {
            currentSectionScore = sPreference.getInt(getString(R.string.water_crisis_score), 0)+10;
            sEditor.putInt(getString(R.string.water_crisis_score), currentSectionScore);
        }
        currentTotalScore = sPreference.getInt(getString(R.string.total_score), 0)+10;
        sEditor.putInt(getString(R.string.total_score), currentTotalScore);
        sEditor.apply();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuestionsActivity.this, HomePage.class);
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

    private void goToNextQuestion()
    {
        canGoToNext = false;
        if(questionNumber >2)
        {
            SharedPreferences.Editor sEditor = sPreference.edit();
            Intent intent = new Intent(QuestionsActivity.this, ImageQuestionActivity.class);
            intent.putExtra(getString(R.string.question_type), questionType);
            startActivity(intent);
        }
        else
        {
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupID);
            rg.clearCheck();
            getQuestionnaireAndQuestion();
            wrongAns.setVisibility(View.GONE);
            rightAns.setVisibility(View.GONE);
            tutQuestionPageText.setVisibility(View.GONE);
            submitBtn.setVisibility(View.VISIBLE);

        }
    }
}
