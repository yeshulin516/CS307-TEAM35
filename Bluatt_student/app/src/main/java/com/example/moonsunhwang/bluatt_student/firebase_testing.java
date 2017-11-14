package com.example.moonsunhwang.bluatt_student;

import com.google.firebase.database.*;

public class firebase_testing {

    private static DatabaseReference mDatabase;

    public static void main(String[] args) {

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference("Students");

        Student stu1 = new Student("Justin", "boudrej");

        //mDatabase.child("Students").setValue(stu1.username);
        ref1.setValue(stu1);

    }
}
