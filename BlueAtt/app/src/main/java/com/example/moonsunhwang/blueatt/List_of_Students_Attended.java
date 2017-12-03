package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class List_of_Students_Attended extends AppCompatActivity {

    //TODO create list view of students who attended

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__students__attended);

        final Button btn = (Button)findViewById(R.id.modify_attendance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSuccessMessage(btn);

            }
        });


        Button home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_of_Students_Attended.this, Main_Page.class));
            }
        });

    }

    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Success!");
        success_message.setMessage("Selected students' attendance record for this day has been modified to:\n'Absent'.");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(List_of_Students_Attended.this, Attendance_class_on_date.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }

}
