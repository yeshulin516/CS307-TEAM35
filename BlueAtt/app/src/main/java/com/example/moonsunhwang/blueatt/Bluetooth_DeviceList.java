package com.example.moonsunhwang.blueatt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.moonsunhwang.blueatt.Bluetooth_MainPage.DispAddress;
import static com.example.moonsunhwang.blueatt.Bluetooth_MainPage.DispName;

public class Bluetooth_DeviceList extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth__device_list);

        lv = (ListView)findViewById(R.id.listView);

        DispAddress.clear();
        DispName.clear();
        for (String s : Bluetooth_MainPage.Name) {
            DispName.add(s);
        }
        for (String s : Bluetooth_MainPage.Address) {
            DispAddress.add(s);
        }

        Toast.makeText(getApplicationContext(), "Showing Scanned Devices", Toast.LENGTH_SHORT).show();


        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DispAddress);

        lv.setAdapter(adapter);

        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DispName);



    }
}
