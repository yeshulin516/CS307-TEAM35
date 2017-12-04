package com.example.moonsunhwang.bluatt_student;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    //TODO get these values from UI
    static String studentID = "";
    static String courseID = "CS307";
    static String deviceID;
    static String currentAtt;
    static boolean attMsg = false;
    static boolean abtMsg = false;



    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference records = database.getReference("Records");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button)findViewById(R.id.register);

        /* for (int i = 1; i < 31; i++) {
            if (i < 10)
                records.child("CS307").child("shulin1").child("11-0" + i + "-17").setValue("Y");
            else if (i > 9 && i < 20)
                records.child("CS307").child("shulin1").child("11-" + i + "-17").setValue("N");
            else
                records.child("CS307").child("shulin1").child("11-" + i + "-17").setValue("Y");
        }
        records.child("CS307").child("shulin1").child("12-01-17").setValue("N");
        records.child("CS307").child("shulin1").child("12-02-17").setValue("Y");
        records.child("CS307").child("shulin1").child("12-03-17").setValue("Y");

        for (int i = 1; i < 31; i++) {
            if (i < 10)
                records.child("CS307").child("moon1").child("11-0" + i + "-17").setValue("N");
            else if (i > 9 && i < 20)
                records.child("CS307").child("moon1").child("11-" + i + "-17").setValue("Y");
            else
                records.child("CS307").child("moon1").child("11-" + i + "-17").setValue("Y");
        }
        records.child("CS307").child("moon1").child("12-01-17").setValue("N");
        records.child("CS307").child("moon1").child("12-02-17").setValue("Y");
        records.child("CS307").child("moon1").child("12-03-17").setValue("Y");
        */
        if (!studentID.equals("")) {
            //automatically get student's device ID
            students.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot node : dataSnapshot.getChildren()) {

                        if (node.getKey().equals(MainActivity.studentID)) {
                            deviceID = node.getValue().toString();
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            //listens for attendance record to be added for current student
            records.child(courseID).child(studentID).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //prints out if attendance is added for current day
                    if (dataSnapshot.getKey().toString().equals(getDate())) {
                        currentAtt = dataSnapshot.getValue().toString();
                        showAttendance(btn);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    //prints out if attendance is changed for current day
                    if (dataSnapshot.getKey().toString().equals(getDate())) {
                        currentAtt = dataSnapshot.getValue().toString();
                        showAttendance(btn);
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());

                }
            });
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Register_Device.class));
            }
        });

        Button btn2 = (Button)findViewById(R.id.update);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Update_Device.class));
            }
        });

        Button btn3 = (Button)findViewById(R.id.attendance_trend_and_data);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Select_Class.class));
            }
        });

        Button btn4 = (Button)findViewById(R.id.feedback);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Give_Feedback.class));
            }
        });


    }

    public void showAttendance(View view) {

        // setup the alert builder

        if (currentAtt.equals("Y") && attMsg != true) {
            AlertDialog.Builder success_message = new AlertDialog.Builder(this);

            success_message.setTitle("Today's Attendance!");
            success_message.setMessage(courseID + " has marked you as attended for " + getDate() + "!");
            attMsg = true;

            // add a button
            success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            // create and show the alert dialog
            AlertDialog successMessage = success_message.create();
            successMessage.show();

        }
        else if (currentAtt.equals("N") && abtMsg != true){
            AlertDialog.Builder success_message = new AlertDialog.Builder(this);

            success_message.setTitle("Today's Attendance!");
            success_message.setMessage(courseID + " has marked you as absent for " + getDate() + "!");
            abtMsg = true;

            // add a button
            success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            // create and show the alert dialog
            AlertDialog successMessage = success_message.create();
            successMessage.show();
        }

    }

    public static String getDate() {
        DateFormat df = new SimpleDateFormat("MM-dd-yy");

        Date dateobj = new Date();
        return df.format(dateobj);
    }

}
