package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Scanning_time_intervals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_time_intervals);

        Button twenty_sec = (Button)findViewById(R.id.twenty_seconds);
        twenty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

        Button forty_sec = (Button)findViewById(R.id.fourty_seconds);
        forty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

        Button sixty_sec = (Button)findViewById(R.id.sixty_seconds);
        sixty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

        Button eighty_sec = (Button)findViewById(R.id.eighty_seconds);
        eighty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

        Button hundred_sec = (Button)findViewById(R.id.hundred_seconds);
        hundred_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

        Button hundredAndTwenty_sec = (Button)findViewById(R.id.hunderedAndTwenty_seconds);
        hundredAndTwenty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_time_intervals.this, Scanning_for_attendance.class));
            }
        });

    }
}
