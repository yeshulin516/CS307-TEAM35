package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Select_Date_Result extends AppCompatActivity {
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__date__result);

        Intent intent = getIntent();
        if(intent != null) {

            day = intent.getIntExtra("Day", 0);
            month = intent.getIntExtra("Month", 0);
            year = intent.getIntExtra("Year", 0);


            TextView tv = (TextView)findViewById(R.id.date_TextView);
            tv.setText(String.valueOf(month)+ "/" + String.valueOf(day) + ", " + String.valueOf(year));

            //use date data
            if (month == 11 && day == 18) {
                TextView attendance = (TextView) findViewById(R.id.textView);
                attendance.setText("You have NOT attended");
            } else {
                TextView attendance = (TextView) findViewById(R.id.textView);
                attendance.setText("You have YES attended");
            }




            Button btn = (Button) findViewById(R.id.home);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Select_Date_Result.this, MainActivity.class));
                }
            });

        }

    }
}
