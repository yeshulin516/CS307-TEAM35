package com.example.moonsunhwang.bluatt_student;

import java.util.Scanner;

public class Bluetooth {
	private String[] attended = new String[1000];
	private String[] ignored = new String [1000];
	int count = 0;
	int ignoreCount = 0;
	
	public Bluetooth(){};
	
	public String[] getAttendance() {
		return attended;
	}
	
	private void addAttendance(String id, Course course) {
		boolean already = false;
		for (int i = 0; i < count; i ++) {
			if (attended[i].equals(id))
				already = true;
		}
		if (already == false && course.inRoster(id)) {
			attended[count] = id;
			count ++;
		}
	}
	
	private void addIgnored(String id) {
		boolean already = false;
		for (int i = 0; i < ignoreCount; i ++) {
			if (ignored[i].equals(id))
				already = true;
		}
		if (already == false) {
			ignored[ignoreCount] = id;
			ignoreCount ++;
		}
	}
	
	public void checkAttendance(Course course) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the Student ID you would like to record for " + course.getName() + " followed by ENTER. Enter \"done\" when complete.");
		String stu = scan.next();
		while (!(stu.equals("done"))) {
			if (course.inRoster(stu)) {
				addAttendance(stu, course);
				System.out.println("Student ID " + stu + "'s attendance has been recorded for " + course.getName());
			}
			else {
				addIgnored(stu);
				System.out.println("Student ID " + stu + " is not registered in this course");
			}
			stu = scan.next();
		}
		System.out.println("Attendance Breakdown for " + course.getName() + ":");
		System.out.println("\tStudents Attended: ");
		for (int i = 0; i < count; i ++) 
			System.out.println("\t\t" + attended[i]);
		String[] absent = course.getAbsent(attended, count);
		System.out.println("\tStudents Absent: ");
		int x = 0;
		while (absent[x] != null) {
			System.out.println("\t\t" + absent[x]);
			x++;
		}
		System.out.println("\tStudents Ignored: ");
		for (int i = 0; i < ignoreCount; i ++)
			System.out.println("\t\t" + ignored[i]);
		System.out.println("Would you like to save this attendance entry? (y/n)");
		String ans = scan.next();
		if (ans.equals("y")) {
			System.out.println("Please enter the date to record in format DDMMYY.");
			String date = scan.next();
			course.addLogEntry(date, course.idToObj(attended, count), course.idToObj(absent, course.getAbsCount()), count, course.getAbsCount());
			System.out.println("Okay, attendance has been recorded.");
		} //send data
		else
			System.out.println("Okay, attendance has been discarded.");
		attended = new String[1000];
		count = 0;
		ignored = new String[1000];
		ignoreCount = 0;
	}
}
