package com.example.testblueatt;


import android.bluetooth.BluetoothManager;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter mBluetoothAdapter;
    Button b1,b2,b3,b4,b5;
    private Set<BluetoothDevice>pairedDevices;
    ListView lv;
    ListView lv2;
    private static ArrayList<String> DispName = new ArrayList<String>();
    private static ArrayList<String> DispAddress = new ArrayList<String>();
    private static ArrayList<String> Name = new ArrayList<String>();
    private static ArrayList<String> Address = new ArrayList<String>();
    private String n;
    private String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        b1 = (Button) findViewById(R.id.on);
        b2=(Button)findViewById(R.id.visible);
        b3=(Button)findViewById(R.id.ld);
        b4=(Button)findViewById(R.id.off);
        b5=(Button)findViewById(R.id.scan);
        lv = (ListView)findViewById(R.id.listView);
        lv2 = (ListView)findViewById(R.id.listView2);






        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
//        } else {
//            if(areLocationServicesEnabled(this)) {
//                mHandler = new Handler();
//
//                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//                    Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//                final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//                mBluetoothAdapter = bluetoothManager.getAdapter();
//
//                scanLeDevice(true);
//            }
//        }
        /*
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);
        */
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
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String action = intent.getAction();
            //Log.d("myTag", action);
            //Toast.makeText(getApplicationContext(), "Hello3",Toast.LENGTH_SHORT).show();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                if (deviceName == null)
                {
                    deviceName = "[unknown]";
                }
                String deviceHardwareAddress = device.getAddress(); // MAC address
                //Toast.makeText(getApplicationContext(), "Hello",Toast.LENGTH_SHORT).show();
                //Log.i("myTag", device.getName());
                //Log.i("..", deviceHardwareAddress);
                Name.add(deviceName);
                if (!Address.contains(deviceHardwareAddress))
                    Address.add(deviceHardwareAddress);
                //n = deviceName;
                //a = deviceHardwareAddress;
            }
        }
    };

    public void requestBlePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                1);
    }



    public void scan(View v) {
        //Log.d("myTag", "hello2");
        // start looking for bluetooth devices
        Address.clear();
        Name.clear();
        mBluetoothAdapter.startDiscovery();
        //Toast.makeText(getApplicationContext(), "Hello2",Toast.LENGTH_SHORT).show();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);



    }


    public void on(View v) {
        {


            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void off(View v){
        mBluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }

    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View v){
        //pairedDevices = mBluetoothAdapter.getBondedDevices();
        DispAddress.clear();
        DispName.clear();
        for (String s : Name){
            DispName.add(s);
        }
        for (String s : Address){
            DispAddress.add(s);
        }

        //for(String n : Name);
        Toast.makeText(getApplicationContext(), "Showing Scanned Devices",Toast.LENGTH_SHORT).show();

        //ArrayList address = new ArrayList();

       /// for(BluetoothDevice bt : pairedDevices) address.add(bt.getAddress());
        //Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, DispAddress);

        lv.setAdapter(adapter);



        //ArrayList name = new ArrayList();

        //for(BluetoothDevice bt : pairedDevices) name.add(bt.getName());
        //Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter2 = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, DispName);
        lv2.setAdapter(adapter2);
    }

    public void listName(View v){
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        ArrayList name = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) name.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter2 = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, name);
        lv2.setAdapter(adapter2);
    }




}
