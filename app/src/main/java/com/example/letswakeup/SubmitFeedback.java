package com.example.letswakeup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubmitFeedback extends AppCompatActivity {

        EditText emailtext;
        int REQUEST_CODE = 1;
        String userName;
        Button feedbackbtn;
        String user_email;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_submit_feedback);

            SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

            userName = sf.getString("username","");
            user_email = sf.getString("email","");
            feedbackbtn = findViewById(R.id.sendEmail);
            emailtext =  findViewById(R.id.editTextemail);


            // get the feedback string user has written and using Intent
            // go to Emailing app and send the feedback to developer
            feedbackbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String[] developers = {"rahulbhat1203@yahoo.in", "midigarahallishanm.r@husky.neu.edu"};
                    Intent emailSending = new Intent(Intent.ACTION_SEND);
                    emailSending.setType("message/rfc822");

                    emailSending.putExtra(Intent.EXTRA_SUBJECT, "Feedback/Review From User");
                    emailSending.putExtra(Intent.EXTRA_EMAIL, developers);


                    String emailbody = emailtext.getText().toString();
                    emailbody = emailbody + "\n\n From user : " + userName + "\n\nemail:" + user_email;
                    emailSending.putExtra(Intent.EXTRA_TEXT, emailbody);


                    try {
                        startActivityForResult(Intent.createChooser(emailSending, "Feedback...."),REQUEST_CODE);
                    }
                    catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(SubmitFeedback.this, "No emailing app installed.", Toast.LENGTH_SHORT).show();
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
