package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.lang.reflect.Method;

public class TopicVideoPage extends AppCompatActivity {

    String vid ;
    String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
    Button get_quest;
    WebView mWebView;
    private boolean mIsPaused = false;
    String topic;
    TextView typeText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_video_page);
        String media_url = "https://www.youtube.com/watch?v=B-nEYsyRlYo";
        topic = getIntent().getStringExtra("topic");
        WebView youtubeWebView = findViewById(R.id.youtube_web_view); //todo find or bind web view
        String env_save = "Vkq_srFGW5I";
        String global_save = "gUhxcdzRgLQ";
        String wat_poll_save = "sYIoPIstObU";
        String water_cri_save = "DgGlVqZkB8A";

        typeText = (TextView) findViewById(R.id.typeTextID);

        if (topic.equals("env")){
            vid = env_save;
            typeText.setText("ENVIRONMENT");
        }

        if (topic.equals("global_warming")){
            typeText.setText("GLOBAL WARMING");
            vid = global_save;
        }

        if (topic.equals("water_pollution")){
            vid = wat_poll_save;
            typeText.setText("WATER POLLUTION");
        }

        if (topic.equals("water_crisis")){
            vid = water_cri_save;
            typeText.setText("WATER CRISIS");
        }
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

                if(topic.equals("global_warming"))
                {
                    Intent intent = new Intent(TopicVideoPage.this, QuestionsActivity.class);
                    startActivity(intent);
                }
                else if(topic.equals("env"))
                {
                    Intent intent = new Intent(TopicVideoPage.this, ImageQuestionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
