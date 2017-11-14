package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.*;


public class Register_Device extends AppCompatActivity {

    String username;
    String deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__device);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference students = database.getReference("Students");
        final DatabaseReference instructors = database.getReference("Instructors");
        final DatabaseReference courses = database.getReference("Courses");
        final DatabaseReference records = database.getReference("Records");
        final DatabaseReference root = database.getReference("ROOT");



        Button btn = (Button)findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et1 = (EditText) findViewById(R.id.username_input);
                username = et1.getText().toString();

                EditText et2 = (EditText) findViewById(R.id.deviceID_input);
                //Integer deviceID = Integer.valueOf(et2.getText().toString());
                deviceID = et2.getText().toString();


                Intent intent = new Intent(Register_Device.this, Register_Device_Result.class);
                Bundle extras = new Bundle();
                extras.putString("USERNAME",username);
                extras.putString("DEVICE_ID",deviceID);

                //add student tree
                students.child("justin1").child("name").setValue("Justin Boudreau");
                students.child("justin1").child("device ID").setValue("123");

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



                intent.putExtras(extras);
                startActivity(intent);

            }
        });





    }
}
