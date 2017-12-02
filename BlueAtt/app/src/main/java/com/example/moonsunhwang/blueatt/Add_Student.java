package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Student extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference records = database.getReference("Records");

    EditText txtUserID;
    String userID;
    String deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

        txtUserID = (EditText) findViewById(R.id.student_name);


        final Button btn = (Button)findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtUserID.getText() != null) {
                    userID = txtUserID.getText().toString();

                    //get student's device ID to add with username to course roster
                    students.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            deviceID = dataSnapshot.getValue().toString();
                            //add student to course roster with device ID from database
                            instructors.child(MainActivity.instructorID).child(MainActivity.courseID).child(userID).setValue(deviceID);

                            //add student to current arrayList
                            MainActivity.roster_usernames.add(userID);
                            MainActivity.roster_devices.add(deviceID);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

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
