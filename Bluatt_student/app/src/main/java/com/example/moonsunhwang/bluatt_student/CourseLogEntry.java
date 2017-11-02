package com.example.moonsunhwang.bluatt_student;

public class CourseLogEntry {
	String date;
	Student[] attended = new Student[1000];
	Student[] absent = new Student[1000];
	int numAttended;
	int numAbsent;
	
	public CourseLogEntry(String adate, Student[] aattended, Student[] aabsent, int anumAttended, int anumAbsent) {
		this.date = adate;
		this.attended = aattended;
		this.numAttended = anumAttended;
		this.absent = aabsent;
		this.numAbsent = anumAbsent;
	}
	
	public void printAttended() {
		System.out.println("\t\tStudents Attended: ");
		if (numAttended > 0) {
			for (int i = 0; i < numAttended; i ++)
				System.out.println("\t\t\t" + attended[i].getUserId());
		}
	}
	
	public void printAbsent() {
		System.out.println("\t\tStudents Absent: ");
		if (numAbsent > 0) {
			for (int i = 0; i < numAbsent; i ++)
				System.out.println("\t\t\t" + absent[i].getUserId());
		}
	}
}
