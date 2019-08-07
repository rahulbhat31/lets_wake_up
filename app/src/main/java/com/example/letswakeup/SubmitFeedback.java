package com.example.letswakeup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubmitFeedback extends AppCompatActivity {

        EditText emailtext;
        int REQUEST_CODE = 1;
        String userName;
        Button startBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_submit_feedback);

            SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

            userName = sf.getString("username","");

            startBtn = findViewById(R.id.sendEmail);

            startBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String[] developer = {"rahulbhat1203@yahoo.in", "midigarahallishanm.r@husky.neu.edu"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailtext =  findViewById(R.id.editTextemail);

                    String emailt = emailtext.getText().toString();
                    emailt = emailt + "\n\n From user : " + userName;
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, developer);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback/Review From User");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailt);

                    try {
                        startActivityForResult(Intent.createChooser(emailIntent, "Send mail..."),REQUEST_CODE);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(SubmitFeedback.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Intent intent=new Intent(SubmitFeedback.this, HomePage.class);
            startActivity(intent);
        }

}
