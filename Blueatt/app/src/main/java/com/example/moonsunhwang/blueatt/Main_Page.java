package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);


        Button view_attendance_report = (Button)findViewById(R.id.view_my_attendance_report);
        view_attendance_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Page.this, Attendance_Record.class));
            }
        });

        Button student_record = (Button)findViewById(R.id.attendance_record);
        student_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Page.this, Find_student.class));
            }
        });

        Button import_roster = (Button)findViewById(R.id.import_roster);
        import_roster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Page.this, Import_roster.class));
            }
        });

        Button scan = (Button)findViewById(R.id.scan_for_attendance);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Page.this, Scanning_Select_Course.class));
            }
        });





    }


}
