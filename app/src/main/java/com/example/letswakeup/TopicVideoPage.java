package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.lang.reflect.Method;

public class TopicVideoPage extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    String vid ;
    Button get_quest;
    WebView mWebView;
    private boolean mIsPaused = false;
    String topic;
    TextView typeText;
    ImageView profile;
    TextView name ;
    ImageView home;

    SharedPreferences sPreference;

    int questionNumber;

    Boolean sectionCompleted = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_video_page);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);


        topic = getIntent().getStringExtra("topic");
        WebView youtubeWebView = findViewById(R.id.youtube_web_view);
        WebSettings youtubeSettings = youtubeWebView.getSettings();

        String env_save = "Vkq_srFGW5I";
        String global_save = "gUhxcdzRgLQ";
        String wat_poll_save = "sYIoPIstObU";
        String water_cri_save = "DgGlVqZkB8A";
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

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicVideoPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        typeText = (TextView) findViewById(R.id.typeTextID);

        if (topic.equals(getString(R.string.environment))){
            vid = env_save;
        }

        if (topic.equals(getString(R.string.global_warming))){
            vid = global_save;
        }

        if (topic.equals(getString(R.string.water_pollution))){
            vid = wat_poll_save;
        }

        if (topic.equals(getString(R.string.water_crisis))){
            vid = water_cri_save;
        }

        typeText.setText(topic);

        youtubeSettings.setJavaScriptEnabled(true);
        youtubeSettings.setLoadWithOverviewMode(true);
        youtubeSettings.setUseWideViewPort(true);

        youtubeWebView.loadUrl("https://www.youtube.com/embed/" + vid);



        get_quest = findViewById(R.id.get_quest);

        get_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getQuestionNumber();

                if(questionNumber > 5)
                {
                    checkSectionCompleted();
                    if(sectionCompleted)
                    {
                        Intent intent = new Intent(TopicVideoPage.this, EndPage.class);
                        intent.putExtra(getString(R.string.question_type), topic);
                        startActivity(intent);
                    }
                    else
                    {
                        if(topic.equals(getString(R.string.environment))){
                            Intent intent = new Intent(TopicVideoPage.this, EnvGame.class);
                            intent.putExtra(getString(R.string.question_type), topic);
                            startActivity(intent);
                        }
                        else if(topic.equals(getString(R.string.water_pollution))){
                            Intent intent = new Intent(TopicVideoPage.this, GWGameSimulation1.class);
                            intent.putExtra(getString(R.string.question_type), topic);
                            startActivity(intent);
                        }
                        else if(topic.equals(getString(R.string.global_warming))){
                            Intent intent = new Intent(TopicVideoPage.this, GlobalWarmingGame.class);
                            intent.putExtra(getString(R.string.question_type), topic);
                            startActivity(intent);
                        }
                        else if(topic.equals(getString(R.string.water_crisis))) {
                            Intent intent = new Intent(TopicVideoPage.this, WaterCrisis.class);
                            intent.putExtra(getString(R.string.question_type), topic);
                            startActivity(intent);
                        }

                    }
                }
                else if(questionNumber > 2)
                {
                    Intent intent = new Intent(TopicVideoPage.this, ImageQuestionActivity.class);
                    intent.putExtra(getString(R.string.question_type), topic);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(TopicVideoPage.this, QuestionsActivity.class);
                    intent.putExtra(getString(R.string.question_type), topic);
                    startActivity(intent);
                }

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicVideoPage.this, profile.class);
                startActivity(intent);
            }
        });


    }


    private void getQuestionNumber(){

        if(topic.equals(getString(R.string.environment)))
        {
            questionNumber = sPreference.getInt(getString(R.string.env_ques_no), 0);
            setQuestionNumberForImageQuestion();

        }
        else if(topic.equals(getString(R.string.water_pollution)))
        {
            questionNumber = sPreference.getInt(getString(R.string.water_poll_ques_no), 0);
            setQuestionNumberForImageQuestion();

        }
        else if(topic.equals(getString(R.string.global_warming)))
        {
            questionNumber = sPreference.getInt(getString(R.string.global_warming_ques_no), 0);
            setQuestionNumberForImageQuestion();
        }
        else if(topic.equals(getString(R.string.water_crisis)))
        {
            questionNumber = sPreference.getInt(getString(R.string.water_crisis_ques_no), 0);
            setQuestionNumberForImageQuestion();
        }
    }


    private void checkSectionCompleted(){

        if(topic.equals(getString(R.string.environment)))
        {
            //Log.d("environmentcompleted", String.valueOf(sPreference.getInt(getString(R.string.env_section_completed_flag), 0) == 1));
            sectionCompleted = (sPreference.getInt(getString(R.string.env_section_completed_flag), 0) == 1) ? true: false;
        }
        else if(topic.equals(getString(R.string.water_pollution)))
        {
            sectionCompleted = (sPreference.getInt(getString(R.string.wp_section_completed_flag), 0) == 1) ? true: false;

        }
        else if(topic.equals(getString(R.string.global_warming)))
        {
            sectionCompleted = (sPreference.getInt(getString(R.string.gw_section_completed_flag), 0) == 1) ? true: false;

        }
        else if(topic.equals(getString(R.string.water_crisis)))
        {
            sectionCompleted = (sPreference.getInt(getString(R.string.wc_section_completed_flag), 0) == 1) ? true: false;

        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TopicVideoPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void setQuestionNumberForImageQuestion(){
        if(questionNumber > 2)
        {

            if(topic.equals(getString(R.string.environment)))
            {
                int newQuestNo = sPreference.getInt(getString(R.string.img_env_ques_no), 0) + questionNumber;
                questionNumber = newQuestNo;
            }
            else if(topic.equals(getString(R.string.water_pollution)))
            {
                int newQuestNo = sPreference.getInt(getString(R.string.img_water_poll_ques_no), 0) + questionNumber;
                questionNumber = newQuestNo;
            }
            else if(topic.equals(getString(R.string.global_warming)))
            {
                int newQuestNo = sPreference.getInt(getString(R.string.img_global_warming_ques_no), 0) + questionNumber;
                questionNumber = newQuestNo;
            }
            else if(topic.equals(getString(R.string.water_crisis)))
            {
                int newQuestNo = sPreference.getInt(getString(R.string.img_water_crisis_ques_no), 0) + questionNumber;
                questionNumber = newQuestNo;
            }

        }
    }


}
