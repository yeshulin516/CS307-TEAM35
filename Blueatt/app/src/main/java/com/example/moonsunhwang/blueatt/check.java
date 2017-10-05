package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        final Button btn = (Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessMessage(btn);
                showFailMessage(btn);
                showQueuedMessage(btn);

            }
        });


    }

    public void showSuccessMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder success_message = new AlertDialog.Builder(this);
        success_message.setTitle("Success!");
        success_message.setMessage("Your attendance was successfully marked for ''course name''");

        // add a button
        success_message.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog successMessage = success_message.create();
        successMessage.show();
    }

    public void showFailMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder fail_message = new AlertDialog.Builder(this);
        fail_message.setTitle("Fail!");
        fail_message.setMessage("Your attendance was NOT successfully marked for ''course name''." +
                "Please try again or contact the instructor");

        // add a button
        fail_message.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog failMessage = fail_message.create();
        failMessage.show();
    }

    public void showQueuedMessage(View view) {

        // setup the alert builder
        AlertDialog.Builder queued_message = new AlertDialog.Builder(this);
        queued_message.setTitle("Queued!");
        queued_message.setMessage("You are queued to be markd present for ''course name'', please wait.");

        // add a button
        queued_message.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog queuedMessage = queued_message.create();
        queuedMessage.show();
    }



}
