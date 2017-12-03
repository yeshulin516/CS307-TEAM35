package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


            //TODO get students attendance value on date
            //records.child("CS307").child(names).addListenerForSingleValueEvent(new ValueEve


                    //use date data
            if (day % 2 == 0) {
                TextView attendance = (TextView) findViewById(R.id.textView);
                attendance.setText("The student has NOT attended");
            } else {
                TextView attendance = (TextView) findViewById(R.id.textView);
                attendance.setText("The student has attended");
            }
        }




        final Button btn = (Button)findViewById(R.id.modify_attendance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO flip attendance value

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

        //TODO add if else statement to show result
        success_message.setMessage("The student's attendance record for this day has been modified to:\n'Absent'.");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Student_Record_Result.this, Main_Page.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }

}
