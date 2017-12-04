package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class Student_Record_Result extends AppCompatActivity {

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

    String attendanceVal;
    String newAttendanceVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__record__result);

        Intent intent = getIntent();
        if(intent != null) {

            day = intent.getIntExtra("Day", 0);
            month = intent.getIntExtra("Month", 0);
            year = intent.getIntExtra("Year", 0);


            TextView tv = (TextView) findViewById(R.id.date_TextView);
            tv.setText(String.valueOf(month) + "/" + String.valueOf(day) + ", " + String.valueOf(year));

            //get students attendance value on date
            records.child(MainActivity.courseID).child(Find_student.studentID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    attendanceVal = "N";
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

        }


        final Button btn = (Button)findViewById(R.id.modify_attendance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get opposite attendance value to set
                if (attendanceVal.equals("Y"))
                    newAttendanceVal = "N";
                else
                    newAttendanceVal = "Y";


                //make change in attendance value to the database
                records.child(MainActivity.courseID).child(Find_student.studentID).child(month + "-0" + day + "-" + Integer.toString(year).substring(2)).setValue(newAttendanceVal);

                showSuccessMessage(btn);

            }
        });

        Button home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Record_Result.this, Main_Page.class));
            }
        });



    }

    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Success!");

        //displaye result of updated attendance
        if (newAttendanceVal.equals("N"))
            success_message.setMessage("The student's attendance record for this day has been modified to:\n'Absent'.");
        else
            success_message.setMessage("The student's attendance record for this day has been modified to:\n'Attended'.");


        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Student_Record_Result.this, Specific_Student_Select_Date.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }

}
