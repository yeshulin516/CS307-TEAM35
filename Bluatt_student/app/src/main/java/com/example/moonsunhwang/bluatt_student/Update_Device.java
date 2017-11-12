package com.example.moonsunhwang.bluatt_student;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update_Device extends AppCompatActivity {

    String username;
    String deviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__device);

        final Button btn = (Button)findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et1 = (EditText) findViewById(R.id.username_input);
                username = et1.getText().toString();

                EditText et2 = (EditText) findViewById(R.id.deviceID_input);
                //Integer deviceID = Integer.valueOf(et2.getText().toString());
                deviceID = et2.getText().toString();

                showSuccessMessage(btn);



            }
        });

    }

    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Success!");
        success_message.setMessage("Your device has been successfully updated!");

        // add a button
        success_message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(Update_Device.this, Update_Device_Result.class);
                        Bundle extras = new Bundle();
                        extras.putString("USERNAME",username);
                        extras.putString("DEVICE_ID",deviceID);
                        intent.putExtras(extras);
                        startActivity(intent);

                    }
                });

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }


}
