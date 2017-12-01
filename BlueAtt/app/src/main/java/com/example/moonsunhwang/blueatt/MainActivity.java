package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    //TODO get these values from UI
    static String instructorID = "jeff1";
    static String courseID = "CS307";

    static ArrayList<String> roster_usernames = new ArrayList<String>();
    static ArrayList<String> roster_devices = new ArrayList<String>();
    static ArrayList<String> roster_attendance = new ArrayList<String>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference records = database.getReference("Records");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button class1 = (Button)findViewById(R.id.class1);
        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pull selected course roster's devices
                instructors.child(instructorID).child(courseID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot node: dataSnapshot.getChildren()) {
                            //System.out.println(node.getKey() + " " + node.getValue());
                            roster_usernames.add(node.getKey());
                            roster_devices.add(node.getValue().toString());
                        }

                        //System.out.println(roster_usernames.toString());
                        //System.out.println(roster_devices.toString());

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

        Button class2 = (Button)findViewById(R.id.class2);
        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

        Button class3 = (Button)findViewById(R.id.class3);
        class3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

        Button class4 = (Button)findViewById(R.id.class4);
        class4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

        Button class5 = (Button)findViewById(R.id.class5);
        class5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

        Button class6 = (Button)findViewById(R.id.class6);
        class6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main_Page.class));
            }
        });

    }

    public static String getDate() {
        DateFormat df = new SimpleDateFormat("MM-dd-yy");

        Date dateobj = new Date();
        return df.format(dateobj);
    }

}
