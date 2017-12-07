package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_Class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__class);

        Button btn1 = (Button)findViewById(R.id.class1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });

        Button btn2 = (Button)findViewById(R.id.class2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });

        Button btn3 = (Button)findViewById(R.id.class3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });

        Button btn4 = (Button)findViewById(R.id.class4);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });

        Button btn5 = (Button)findViewById(R.id.class5);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });

        Button btn6 = (Button)findViewById(R.id.class6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Select_Class.this, Select_Date.class));
            }
        });
    }
}
