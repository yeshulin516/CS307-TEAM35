package com.example.moonsunhwang.blueatt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class List_of_Students_Missed extends AppCompatActivity {

    //TODO create list view of students who missed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__students__missed);

        ListView lv = (ListView)findViewById(R.id.listView);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Attendance_class_on_date.absentList);

        lv.setAdapter(adapter);

        Button home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_of_Students_Missed.this, Main_Page.class));
            }
        });

    }

}
