package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_my_attendance_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_attendance_report);

        Button class1 = (Button)findViewById(R.id.class1);
        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

        Button class2 = (Button)findViewById(R.id.class2);
        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

        Button class3 = (Button)findViewById(R.id.class3);
        class3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

        Button class4 = (Button)findViewById(R.id.class4);
        class4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

        Button class5 = (Button)findViewById(R.id.class5);
        class5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

        Button class6 = (Button)findViewById(R.id.class6);
        class6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(View_my_attendance_report.this, Attendance_Record.class));
            }
        });

    }
}