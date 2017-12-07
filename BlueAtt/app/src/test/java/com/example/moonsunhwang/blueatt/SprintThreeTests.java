package com.example.moonsunhwang.blueatt;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by michael on 11/30/17.
 */


public class SprintThreeTests {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    final DatabaseReference students = database.getReference("Students");
    final DatabaseReference instructors = database.getReference("Instructors");
    final DatabaseReference courses = database.getReference("Courses");
    final DatabaseReference records = database.getReference("Records");
    @Test
    public void test400() throws Exception {
        for (int i = 0; i < 400; i ++) {
            students.child("TestStudent" + i).setValue("Test");
            MainActivity.roster_usernames.add("TestStudent" + i);
        }
        assertEquals(MainActivity.roster_usernames.size(), 400);
    }

}

