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

import java.util.ArrayList;

public class Attendance_class_on_date extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");

    int totalAttended;
    int totalAbsent;
    double percentageAttended;

    int day;
    int month;
    int year;


    public void getAtt ( ) {
        for (String names : MainActivity.roster_usernames) {

            //pull selected student's attendance record for the course
            records.child("CS307").child(names).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot node: dataSnapshot.getChildren()) {

                        if (node.getKey().toString().equals((month + "-0" + day + "-" + Integer.toString(year).substring(2)))) {

                            MainActivity.roster_attendance.add(node.getValue().toString());

                            //if all students attendance values have been added
                            //calculates values and updates UI
                            if (MainActivity.roster_attendance.size() == MainActivity.roster_usernames.size()) {
                                //count number of student in attendance
                                int totalAttended = 0;

                                for (String att : MainActivity.roster_attendance) {
                                    if (att.equals("Y"))
                                        totalAttended++;
                                }

                                int totalAbsent = MainActivity.roster_attendance.size() - totalAttended;

                                double percentageAttended = (((double)totalAttended) / MainActivity.roster_attendance.size()) * 100;

                                //update UI
                                TextView tvTotalAttended = (TextView) findViewById(R.id.textView1);
                                tvTotalAttended.setText(String.valueOf(totalAttended));

                                TextView tvTotalAbsent = (TextView) findViewById(R.id.textView2);
                                tvTotalAbsent.setText(String.valueOf(totalAbsent));

                                TextView tvPercentageAttended = (TextView) findViewById(R.id.textView3);
                                tvPercentageAttended.setText(String.valueOf(percentageAttended));

                                MainActivity.roster_attendance.clear();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_class_on_date);


        Intent intent = getIntent();
        if(intent != null) {

            day = intent.getIntExtra("Day", 0);
            month = intent.getIntExtra("Month", 0);
            year = intent.getIntExtra("Year", 0);


            getAtt();


            TextView tv = (TextView) findViewById(R.id.date_TextView);
            tv.setText(String.valueOf(month) + "/" + String.valueOf(day) + ", " + String.valueOf(year));

            TextView tvTotalAttended = (TextView) findViewById(R.id.textView1);
            tvTotalAttended.setText(String.valueOf(totalAttended));

            TextView tvTotalAbsent = (TextView) findViewById(R.id.textView2);
            tvTotalAbsent.setText(String.valueOf(totalAbsent));

            TextView tvPercentageAttended = (TextView) findViewById(R.id.textView3);
            tvPercentageAttended.setText(String.valueOf(percentageAttended));
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
