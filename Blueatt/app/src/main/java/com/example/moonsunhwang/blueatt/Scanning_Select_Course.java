package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Scanning_Select_Course extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning__select__course);

        Button class1 = (Button)findViewById(R.id.class1);
        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });

        Button class2 = (Button)findViewById(R.id.class2);
        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });

        Button class3 = (Button)findViewById(R.id.class3);
        class3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });

        Button class4 = (Button)findViewById(R.id.class4);
        class4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });

        Button class5 = (Button)findViewById(R.id.class5);
        class5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });

        Button class6 = (Button)findViewById(R.id.class6);
        class6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Scanning_Select_Course.this, Scanning_time_intervals.class));
            }
        });
    }
}
