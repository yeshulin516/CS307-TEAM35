package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button view_attendance_report = (Button)findViewById(R.id.view_my_attendance_report);
        view_attendance_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View_my_attendance_report.class));
            }
        });

        Button student_record = (Button)findViewById(R.id.attendance_record);
        student_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, View_my_courses.class));
            }
        });

        Button import_roster = (Button)findViewById(R.id.import_roster);
        import_roster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Import_roster.class));
            }
        });

        Button scan = (Button)findViewById(R.id.scan_for_attendance);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Scanning_time_intervals.class));
            }
        });



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference students = database.getReference("Students");

        students.child("justin9").setValue("AA:BB:CC");
        students.child("moon9").setValue("DD:EE:FF");
        students.child("mike9").setValue("GG:HH:II");
        students.child("shulin9").setValue("KK:LL:MM");




    }


}