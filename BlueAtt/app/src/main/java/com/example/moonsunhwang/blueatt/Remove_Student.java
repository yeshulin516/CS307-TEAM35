package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Remove_Student extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__student);

        final Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et1 = (EditText) findViewById(R.id.student_name);
                String input_name = et1.getText().toString();

                //check if input username is in the course roster
                if (MainActivity.roster_usernames.contains(input_name)) {
                    //TODO stop deletion if dont confirm
                    confirmMessage(btn);

                    instructors.child("jeff1").child("CS307").child(input_name).removeValue();
                    records.child("CS307").child(input_name).removeValue();

                    //remove student's username and device ID from global array list
                    int index =  MainActivity.roster_usernames.indexOf(input_name);
                    MainActivity.roster_usernames.remove(index);
                    MainActivity.roster_devices.remove(index);

                }
                //TODO display error meassage, student not in course
                else;


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
