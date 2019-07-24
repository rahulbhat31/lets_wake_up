package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
    String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
    Button get_quest;
    WebView mWebView;
    private boolean mIsPaused = false;
    String topic;
    TextView typeText;
    ImageView profile;
    TextView name ;
    ImageView home;

    SharedPreferences sPreference;
    SharedPreferences.Editor sEditor;

    int questionNumber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_video_page);

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);


        String media_url = "https://www.youtube.com/watch?v=B-nEYsyRlYo";
        topic = getIntent().getStringExtra("topic");
        WebView youtubeWebView = findViewById(R.id.youtube_web_view); //todo find or bind web view
        String env_save = "Vkq_srFGW5I";
        String global_save = "gUhxcdzRgLQ";
        String wat_poll_save = "sYIoPIstObU";
        String water_cri_save = "DgGlVqZkB8A";
        profile = (ImageView)findViewById(R.id.avatar);




        //set Name on top
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


        youtubeWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = youtubeWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        youtubeWebView.loadUrl("https://www.youtube.com/embed/" + vid);



        get_quest = findViewById(R.id.get_quest);

        get_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getQuestionNumber();

                if(questionNumber > 5)
                {
                    Intent intent = new Intent(TopicVideoPage.this, GWGameSimulation1.class);
                    intent.putExtra(getString(R.string.question_type), topic);
                    startActivity(intent);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TopicVideoPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    private void setQuestionNumberForImageQuestion(){
        if(questionNumber > 2)
        {
            int newQuestNo = sPreference.getInt(getString(R.string.img_env_ques_no), 0) + questionNumber;
            questionNumber = newQuestNo;

        }
    }


}
