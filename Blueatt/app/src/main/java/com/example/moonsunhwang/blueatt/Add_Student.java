package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

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
        success_message.setTitle("Success!");
        success_message.setMessage("Successfully added a new student to this class");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Add_Student.this, Attendance_Record.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }

}
