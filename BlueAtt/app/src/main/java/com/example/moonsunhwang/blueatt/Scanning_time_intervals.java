package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Scanning_time_intervals extends AppCompatActivity {

    static int numSeconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_time_intervals);

        Button twenty_sec = (Button)findViewById(R.id.thirty_seconds);
        twenty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 30;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

        Button forty_sec = (Button)findViewById(R.id.one_min);
        forty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 60;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

        Button sixty_sec = (Button)findViewById(R.id.two_min);
        sixty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 120;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

        Button eighty_sec = (Button)findViewById(R.id.three_min);
        eighty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 180;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

        Button hundred_sec = (Button)findViewById(R.id.four_min);
        hundred_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 240;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

        Button hundredAndTwenty_sec = (Button)findViewById(R.id.five_min);
        hundredAndTwenty_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSeconds = 300;
                startActivity(new Intent(Scanning_time_intervals.this, Bluetooth_MainPage.class));
            }
        });

    }
}