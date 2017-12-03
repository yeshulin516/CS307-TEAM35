package com.example.moonsunhwang.bluatt_student;

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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Select_Date_Result extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference records = database.getReference("Records");

    int day;
    int month;
    int year;

    String attendanceVal;

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

            //get students attendance value on date
            records.child(MainActivity.courseID).child(MainActivity.studentID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //get the attendance value on the specified date
                    for (DataSnapshot node : dataSnapshot.getChildren()) {
                        //set the attendance value with the value from the database
                        if (node.getKey().equals((month + "-0" + day + "-" + Integer.toString(year).substring(2))))
                            attendanceVal = node.getValue().toString();
                    }

                    if (attendanceVal.equals("N")) {
                        TextView attendance = (TextView) findViewById(R.id.textView);
                        attendance.setText("The student has NOT attended");
                    }
                    else {
                        TextView attendance = (TextView) findViewById(R.id.textView);
                        attendance.setText("The student has attended");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


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
