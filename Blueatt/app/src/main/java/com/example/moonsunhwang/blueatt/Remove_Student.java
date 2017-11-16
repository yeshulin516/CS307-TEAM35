package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Remove_Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__student);

        final Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmMessage(btn);


            }
        });

    }

    public void confirmMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder confirm_message = new AlertDialog.Builder(this);
        confirm_message.setTitle("Are you sure?");
        confirm_message.setMessage("If you click OK, all the attendance record for the student will be deleted. " +
                "Click OK to remove a student from the class.");

        // add a button
        confirm_message.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

            }
        });

        // add a button
        confirm_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();

                AlertDialog.Builder success_message = new AlertDialog.Builder(Remove_Student.this);
                success_message.setTitle("Success!");
                success_message.setMessage("Successfully removed the student from this class");

                // add a button
                success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        startActivity(new Intent(Remove_Student.this, Attendance_Record.class));

                    }
                });

                // create and show the alert dialog
                AlertDialog successMessage = success_message.create();
                successMessage.show();



            }
        });

        // create and show the alert dialog
        AlertDialog confirmMessage = confirm_message.create();
        confirmMessage.show();
    }


    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Success!");
        success_message.setMessage("Successfully removed the student from this class");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Remove_Student.this, Attendance_Record.class));

            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }


}
