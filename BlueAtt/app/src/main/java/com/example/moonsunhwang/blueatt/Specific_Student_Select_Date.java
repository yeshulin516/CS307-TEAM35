package com.example.moonsunhwang.blueatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class Specific_Student_Select_Date extends AppCompatActivity {

    private int mDay;
    private int mMonth;
    private int mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific__student__select__date);

        Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mYear = calendar.get(Calendar.YEAR);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                mDay = dayOfMonth;
                mMonth = month + 1;
                mYear = year;
            }
        });


        Button btn = (Button)findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Specific_Student_Select_Date.this,Student_Record_Result.class);

                // Put the date values to the intent
                intent.putExtra("Day",mDay);
                intent.putExtra("Month",mMonth);
                intent.putExtra("Year",mYear);

                startActivity(intent);

            }

        });

    }
}