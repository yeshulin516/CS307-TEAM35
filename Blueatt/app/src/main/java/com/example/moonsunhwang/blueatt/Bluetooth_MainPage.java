package com.example.moonsunhwang.blueatt;

import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import java.lang.Object;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.*;
import android.support.v4.app.ActivityCompat;
import android.app.Activity;
import android.Manifest;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Set;

import static com.example.moonsunhwang.blueatt.R.id.info;

public class Bluetooth_MainPage extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");

    private final static int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter mBluetoothAdapter;
    Button b1, b2, b3, b4, b5;
    private Set<BluetoothDevice> pairedDevices;

    static ArrayList<String> DispName = new ArrayList<String>();
    static ArrayList<String> DispAddress = new ArrayList<String>();
    static ArrayList<String> Name = new ArrayList<String>();
    static ArrayList<String> Address = new ArrayList<String>();
    private String n;
    private String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth__main_page);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        b1 = (Button) findViewById(R.id.on);

        b3 = (Button) findViewById(R.id.ld);
        b4 = (Button) findViewById(R.id.off);
        b5 = (Button) findViewById(R.id.scan);


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showSuccessMessage(b5);
            }
        });



        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }


    // Create a BroadcastReceiver for ACTION_FOUND.

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                if (deviceName == null) {
                    deviceName = "[unknown]";
                }
                String deviceHardwareAddress = device.getAddress(); // MAC address

                Name.add(deviceName);
                if (!Address.contains(deviceHardwareAddress))
                    Address.add(deviceHardwareAddress.toLowerCase());
            }
        }
    };

    public void requestBlePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }


    public void scan(View v) {
        // start looking for bluetooth devices
        Address.clear();
        Name.clear();
        mBluetoothAdapter.startDiscovery();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);


    }


    public void on(View v) {
        {

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void off(View v) {
        mBluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();

        //compares roster's device list to scanned devices
        for (String roster_device : MainActivity.roster_devices) {
            if (Address.contains(roster_device)) {
                MainActivity.roster_attendance.add("Y");
            }
            else
                MainActivity.roster_attendance.add("N");
        }

        //

        for (int i = 0; i < MainActivity.roster_usernames.size(); i++ ) {
            records.child(MainActivity.courseID).child(MainActivity.roster_usernames.get(i)).child(MainActivity.getDate()).setValue(MainActivity.roster_attendance.get(i));
        }

        MainActivity.roster_attendance.clear();

        //System.out.println(MainActivity.roster_attendance.toString());
    }



    public void list(View v) {

        startActivity(new Intent(Bluetooth_MainPage.this, Bluetooth_DeviceList.class));


    }

    public void listName(View v) {
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        ArrayList name = new ArrayList();

        for (BluetoothDevice bt : pairedDevices) name.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name);

    }

    public void showSuccessMessage(View view) {

        final AlertDialog alertDialog;

        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Please wait 10 seconds for scanning.");
        alertDialog.setMessage("00:10");
        alertDialog.show();   //

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage("00:"+ (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                alertDialog.dismiss();
            }
        }.start();

    }


}