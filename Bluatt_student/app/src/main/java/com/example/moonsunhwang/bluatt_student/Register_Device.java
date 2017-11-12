package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register_Device extends AppCompatActivity {

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
                intent.putExtras(extras);
                startActivity(intent);

            }
        });





    }
}
