package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.register);

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



        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference students = database.getReference("Students");
        final DatabaseReference instructors = database.getReference("Instructors");
        final DatabaseReference courses = database.getReference("Courses");
        final DatabaseReference records = database.getReference("Records");
        final DatabaseReference root = database.getReference("ROOT");

        final ArrayList<String> stuList = new ArrayList<>();

        students.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot node: dataSnapshot.getChildren()) {
                    String value = node.toString();

                    stuList.add(value);
                }

                System.out.println(stuList.size());

                for (String values : stuList) {
                    System.out.println(values);
                    //Log.i("students", values);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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





        //add student tree
        students.child("justin1").child("name").setValue("Justin Boudreau");
        students.child("justin1").child("device ID").setValue("123");

        students.child("mike1").child("name").setValue("Mike Engright");
        students.child("mike1").child("device ID").setValue("456");

        //add instructor tree
        instructors.child("jeff1").setValue("Jeff Turkstra");
        instructors.child("jeff1").child("CS307").setValue("0");
        instructors.child("jeff1").child("CS408").setValue("0");

        //add course tree
        courses.child("CS307").child("title").setValue("Software Engineering I");
        courses.child("CS307").child("instructor").setValue("jeff");
        courses.child("CS307").child("days").setValue("TR");
        courses.child("CS307").child("start time").setValue("3pm");
        courses.child("CS307").child("end time").setValue("4:15pm");

        //add record tree
        records.child("CS307").child("justin1").child("11-14-17").setValue("Y");
        records.child("CS307").child("shulin1").child("11-14-17").setValue("Y");
        records.child("CS307").child("mike1").child("11-14-17").setValue("Y");
        records.child("CS307").child("moon1").child("11-14-17").setValue("N");

        records.child("CS307").child("justin1").child("11-16-17").setValue("N");
        records.child("CS307").child("shulin1").child("11-16-17").setValue("Y");
        records.child("CS307").child("mike1").child("11-16-17").setValue("N");
        records.child("CS307").child("moon1").child("11-16-17").setValue("Y");


                /* USE ONLY ONE TREE FOR ALL VALUES */

        //add instructors, names, and courses
        root.child("Instructors").child("jeff1").child("name").setValue("Jeff Turkstra");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("course title").setValue("Software Engineering I");
        root.child("Instructors").child("jeff1").child("Courses").child("CS408").child("course title").setValue("Software Testing");

        root.child("Instructors").child("buster1").child("name").setValue("Buster Dunsmore");
        root.child("Instructors").child("buster1").child("Courses").child("CS180").child("course title").setValue("Java Programming");
        root.child("Instructors").child("buster1").child("Courses").child("CS307").child("course title").setValue("Software Engineering I");

        //add course info
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("days of week").setValue("TR");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("start time").setValue("3pm");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("end time").setValue("4:15pm");

        //add students to courses
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Students").child("justin1").setValue("Justin");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Students").child("moon1").setValue("Moon");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Students").child("mike1").setValue("Mike");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Students").child("shulin1").setValue("Shulin");


        //add devices to courses
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Devices").child("123").setValue("justin1");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Devices").child("456").setValue("moon1");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Devices").child("789").setValue("mike1");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Devices").child("000").setValue("shulin1");

        //add records for each student
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Records").child("11-14-17").child("shulin1").setValue("Y");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Records").child("11-14-17").child("moon1").setValue("Y");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Records").child("11-14-17").child("justin1").setValue("Y");
        root.child("Instructors").child("jeff1").child("Courses").child("CS307").child("Records").child("11-14-17").child("mike1").setValue("Y");


    }


}
