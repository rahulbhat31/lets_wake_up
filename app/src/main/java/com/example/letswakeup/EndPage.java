package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class EndPage extends AppCompatActivity {


    private Button home_btn;
    private VideoView mVideoView;

    int myCurrentVideoPosition;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);


        home_btn = findViewById(R.id.homebtn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndPage.this, HomePage.class);
                startActivity(intent);
            }
        });

        mVideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bubble);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        if(myCurrentVideoPosition !=0){

        }
        else
        {
            mVideoView.start();
        }

        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        myCurrentVideoPosition = preferences.getInt("lastPosition",  0);


        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });


    }

    @Override
    protected void onPause(){
        super.onPause();

        myCurrentVideoPosition = mVideoView.getCurrentPosition();
        mVideoView.pause();

        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastPosition",  myCurrentVideoPosition);
        editor.apply();

    }

    @Override
    protected void onResume(){

        super.onResume();
        mVideoView.seekTo(myCurrentVideoPosition);
        mVideoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("lastPositionInVideo", myCurrentVideoPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);
        myCurrentVideoPosition = savedInstanceState.getInt("lastPositionInVideo");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EndPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
