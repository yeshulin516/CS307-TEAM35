package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Find_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_student);

        final Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSuccessMessage(btn);

            }
        });

    }


    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Student Found!");
        success_message.setMessage("Found a student. Now, please select a date you wish to view attendance record");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Find_student.this, Specific_Student_Select_Date.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }


}
