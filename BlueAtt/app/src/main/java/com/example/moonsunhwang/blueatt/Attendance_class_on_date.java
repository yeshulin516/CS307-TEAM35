package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Attendance_class_on_date extends AppCompatActivity {

    int day;
    int month;
    int year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_class_on_date);

        Intent intent = getIntent();
        if(intent != null) {

            day = intent.getIntExtra("Day", 0);
            month = intent.getIntExtra("Month", 0);
            year = intent.getIntExtra("Year", 0);


            TextView tv = (TextView) findViewById(R.id.date_TextView);
            tv.setText(String.valueOf(month) + "/" + String.valueOf(day) + ", " + String.valueOf(year));
        }

        Button attended = (Button)findViewById(R.id.view_students_who_attended_button);
        attended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attendance_class_on_date.this, List_of_Students_Attended.class));
            }
        });

        Button missed = (Button)findViewById(R.id.view_students_who_missed_button);
        missed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attendance_class_on_date.this, List_of_Students_Missed.class));
            }
        });

        Button home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attendance_class_on_date.this, MainActivity.class));
            }
        });



    }
}
