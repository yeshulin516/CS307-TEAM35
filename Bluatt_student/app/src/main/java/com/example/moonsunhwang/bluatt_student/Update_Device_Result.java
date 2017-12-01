package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Update_Device_Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__device__result);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String username = extras.getString("USERNAME");
        String device_id = extras.getString("DEVICE_ID");

        TextView usernameTextView = (TextView)findViewById(R.id.username_TextView);
        usernameTextView.setText(username);

        TextView deviceIDTextView = (TextView)findViewById(R.id.date_TextView);
        deviceIDTextView.setText(device_id);

        Button btn = (Button)findViewById(R.id.home);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Update_Device_Result.this, MainActivity.class));
            }
        });

    }
}
