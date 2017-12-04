package com.example.moonsunhwang.bluatt_student;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.*;


public class Register_Device extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");

    String username;
    String deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__device);

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

                MainActivity.studentID = username;



                //TODO check if device ID is null otherwise dont add
                if (!deviceID.equals(""))
                    students.child(username).setValue(deviceID);


                MainActivity.abtMsg = false;
                MainActivity.attMsg = false;

                intent.putExtras(extras);
                startActivity(intent);

            }
        });

    }

    public void showFailMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Fail!");
        success_message.setMessage("Your device ID does not match currently registered device!");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {


            }
        });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }
}
