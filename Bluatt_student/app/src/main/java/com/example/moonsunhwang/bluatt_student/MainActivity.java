package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Student stu1 = new Student("Student 1", "01");
        Student stu2 = new Student("Student 2", "02");
        Student stu3 = new Student("Student 3", "03");
        Test.roster[0] = stu1;
        Test.roster[1] = stu2;
        Test.roster[2] = stu3;

        Instructor ins1 = new Instructor("Instructor 1", "11");
        Instructor ins2 = new Instructor("Instructor 2", "12");
        Instructor ins3 = new Instructor("Instructor 3", "13");
        Test.instList[0] = ins1;
        Test.instList[1] = ins2;
        Test.instList[2] = ins3;

        Course course1 = new Course("Course 1", ins1);
        Course course2 = new Course("Course 2", ins2);
        Course course3 = new Course("Course 3", ins3);
        Test.courses[0] = course1;
        Test.courses[1] = course2;
        Test.courses[2] = course3;

        stu1.addCourse(course1);
        stu1.addCourse(course3);
        stu2.addCourse(course2);
        stu2.addCourse(course1);
        stu3.addCourse(course3);
        stu3.addCourse(course2);
        stu3.addCourse(course1);

        ins1.addCourse(course1);
        ins2.addCourse(course2);
        ins3.addCourse(course3);


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

    }


}
