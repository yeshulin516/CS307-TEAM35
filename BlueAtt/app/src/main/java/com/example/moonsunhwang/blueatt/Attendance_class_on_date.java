package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import

import java.util.ArrayList;

public class Attendance_class_on_date extends AppCompatActivity {

    static ArrayList<String> roster_usernames = new ArrayList<String>();
    static ArrayList<String> roster_devices = new ArrayList<String>();
    static ArrayList<String> roster_attendance = new ArrayList<String>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");
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

            //TODO pull whole roster attendance on the selected date
            for (String s : MainActivity.roster_usernames) {
                records.child("jeff1").child("CS307").child(s).addListenerForSingleValueEvent(new ValueEventListener(.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot node: dataSnapshot.getChildren()) {
                            //System.out.println(node.getKey() + " " + node.getValue());
                            if (node.getKey().equals(month + "-" + day + "-" + Integer.toString(year).substring(2)));
                            {
                                MainActivity.roster_attendance.add(node.getValue().toString());
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
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
                startActivity(new Intent(Attendance_class_on_date.this, Main_Page.class));
            }
        });



    }
}
