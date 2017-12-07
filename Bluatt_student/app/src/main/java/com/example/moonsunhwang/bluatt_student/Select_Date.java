package com.example.moonsunhwang.bluatt_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static android.R.attr.button;

public class Select_Date extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference records = database.getReference("Records");

    private int mDay;
    private int mMonth;
    private int mYear;


    int attended = 0;
    int absent = 0;

    public void setCounters () {
        records.child(MainActivity.courseID).child(MainActivity.studentID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot node : dataSnapshot.getChildren()) {

                    if (node.getValue().toString().equals("Y"))
                        attended++;
                    else
                        absent++;

                    if (attended + absent == dataSnapshot.getChildrenCount()) {
                        TextView tvAttended = (TextView) findViewById(R.id.textView1);
                        tvAttended.setText(String.valueOf(attended));

                        TextView tvAbsent = (TextView) findViewById(R.id.textView2);
                        tvAbsent.setText(String.valueOf(absent));

                        attended = 0;
                        absent = 0;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__date);


        setCounters();

        TextView tvAttended = (TextView) findViewById(R.id.textView1);
        tvAttended.setText(String.valueOf(attended));

        TextView tvAbsent = (TextView) findViewById(R.id.textView2);
        tvAbsent.setText(String.valueOf(absent));

        Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mYear = calendar.get(Calendar.YEAR);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                mDay = dayOfMonth;
                mMonth = month + 1;
                mYear = year;
            }
        });


        Button btn = (Button)findViewById(R.id.submit_date);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Select_Date.this,Select_Date_Result.class);

                // Put the date values to the intent
                intent.putExtra("Day",mDay);
                intent.putExtra("Month",mMonth);
                intent.putExtra("Year",mYear);

                startActivity(intent);

            }

        });


    }



}
