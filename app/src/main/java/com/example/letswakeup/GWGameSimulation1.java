package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GWGameSimulation1 extends AppCompatActivity {


    ImageView plasticBottleImg;
    ImageView plasticBagImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwgame_simulation1);

        plasticBottleImg = (ImageView) findViewById(R.id.bottleImgID);
        plasticBagImg = (ImageView) findViewById(R.id.plasticBagImgID);

        plasticBagImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plasticBagImg.setVisibility(View.INVISIBLE);
            }
        });


        plasticBottleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plasticBottleImg.setVisibility(View.INVISIBLE);
            }
        });

    }


}
