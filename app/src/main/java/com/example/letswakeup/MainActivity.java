package com.example.letswakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Calendar myCalendar = Calendar.getInstance();
    private EditText etDate;

    EditText tv ;
    RadioGroup gendr;
    EditText email;
    Button save;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        /*sPreference.edit().clear().commit();*/

        SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userName = sf.getString("username",null);
        if(userName  != null){
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            intent.putExtra("Name", userName);
            startActivity(intent);
        }



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etDate.setText(sdf.format(myCalendar.getTime()));
            }

        };


        etDate = (EditText) findViewById(R.id.et_date);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv = findViewById(R.id.enterName);
                gendr = (RadioGroup)findViewById(R.id.gender);
                email = findViewById(R.id.email);
                etDate = (EditText) findViewById(R.id.et_date);
                Editable name = tv.getText();
                RadioButton morf = (RadioButton)findViewById(gendr.getCheckedRadioButtonId());
                SharedPreferences sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sf.edit();

                editor.putString("username" , tv.getText().toString());
                editor.putString("dob" , etDate.getText().toString());
                editor.putString("gender" , morf.getText().toString());
                editor.putString("email" , email.getText().toString());

                editor.apply();


                Intent intent = new Intent(MainActivity.this, HomePage.class);
                intent.putExtra("Name", name.toString());
                startActivity(intent);
            }
        });



    }
}
