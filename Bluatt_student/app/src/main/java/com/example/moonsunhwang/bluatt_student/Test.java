package com.example.moonsunhwang.bluatt_student;

import java.util.Scanner;
public class Test {

	static Student[] roster = new Student[10];
	static Instructor[] instList = new Instructor[3];
	static Course[] courses = new Course[3];
	static String[] feedback = new String[10];
	static int feedbackCount = 0;
	static int stuCount = 3;

	public static void main(String[] args) {
		
		Student stu1 = new Student("Student 1", "01");
		Student stu2 = new Student("Student 2", "02");
		Student stu3 = new Student("Student 3", "03");
		roster[0] = stu1;
		roster[1] = stu2;
		roster[2] = stu3;
		
		Instructor ins1 = new Instructor("Instructor 1", "11");
		Instructor ins2 = new Instructor("Instructor 2", "12");
		Instructor ins3 = new Instructor("Instructor 3", "13");
		instList[0] = ins1;
		instList[1] = ins2;
		instList[2] = ins3;
		
		Course course1 = new Course("Course 1", ins1);
		Course course2 = new Course("Course 2", ins2);
		Course course3 = new Course("Course 3", ins3);
		courses[0] = course1;
		courses[1] = course2;
		courses[2] = course3;
		
		stu1.addCourse(course1);
		stu1.addCourse(course3);
		stu2.addCourse(course2);
		stu2.addCourse(course1);
		stu3.addCourse(course3);
		stu3.addCourse(course2);
		stu3.addCourse(course1);
		
		ins1.addCourse(course1);
		ins2.addCourse(course2);
		ins3.addCourse(course3);
		
		/*Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to BlueAtt! Please select an option from the list below.");
		boolean cont = true;
		while (cont == true) {
			System.out.println("1. Print Student Information.\n"
					+ "2. Print Instructor Information.\n"
					+ "3. Print Course Information.\n"
					+ "4. Enter Attendance for Courses and Date\n"
					+ "5. Print entire attendance record.\n"
					+ "6. Print Entire Attendance record for a course on a specific date.\n"
					+ "7. Exit");
			int key = scan.nextInt();
			switch (key) {
			case 1:
				System.out.println("Please enter the ID of the student you would like to view, or enter ALL to print all students:");
				for (int i = 0; i < 3; i++)
					System.out.println("Student ID: " + roster[i].getUserId() + " - Student Name: " + roster[i].getName());
				String input = scan.next();
				if (input.equals("01"))
					stu1.printInfo();
				else if (input.equals("02"))
					stu2.printInfo();
				else if (input.equals("03"))
					stu3.printInfo();
				else if (input.equalsIgnoreCase("ALL")) {
					stu1.printInfo();
					stu2.printInfo();
					stu3.printInfo();
				}
				else
					System.out.println("Unrecognized input; returning to main menu.");
				break;
				
			case 2:
				System.out.println("Please enter the ID of the instructor you would like to view, or enter ALL to print all instructors:");
				for (int i = 0; i < 3; i++)
					System.out.println("Instructor ID: " + instList[i].getUserId() + " - Instructor Name: " + instList[i].getName());
				input = scan.next();
				if (input.equals("11"))
					ins1.printInfo();
				else if (input.equals("12"))
					ins2.printInfo();
				else if (input.equals("13"))
					ins3.printInfo();
				else if (input.equalsIgnoreCase("ALL")) {
					ins1.printInfo();
					ins2.printInfo();
					ins3.printInfo();
				}
				else
					System.out.println("Unrecognized input; returning to main menu.");
				break;
				
			case 3:
				System.out.println("Please enter the name of the course you would like to view, or enter ALL to print all courses:");
				for (int i = 0; i < 3; i++)
					System.out.println("Course ID: 0" + (i + 1) + " - Course Name: " + courses[i].getName() + " - Professor Name: " + courses[i].getInstructor().getName());
				input = scan.next();
				if (input.equalsIgnoreCase("01"))
					course1.printInfo();
				else if (input.equalsIgnoreCase("02"))
					course2.printInfo();
				else if (input.equalsIgnoreCase("03"))
					course3.printInfo();
				else if (input.equalsIgnoreCase("ALL")) {
					course1.printInfo();
					course2.printInfo();
					course3.printInfo();
				}
				else
					System.out.println("Unrecognized input; returning to main menu.");
				break;
				
			case 4:System.out.println("Please enter the name of the course you would like to enter attendance for, or enter ALL to enter attendance for all courses:");
				Bluetooth test = new Bluetooth();
				for (int i = 0; i < 3; i++)
					System.out.println("Course ID: 0" + (i + 1) + " - Course Name: " + courses[i].getName() + " - Professor Name: " + courses[i].getInstructor().getName());
				input = scan.next();
				if (input.equalsIgnoreCase("01"))
					test.checkAttendance(course1);
				else if (input.equalsIgnoreCase("02"))
					test.checkAttendance(course2);
				else if (input.equalsIgnoreCase("03"))
					test.checkAttendance(course3);
				else if (input.equalsIgnoreCase("ALL")) {
					test.checkAttendance(course1);
					test.checkAttendance(course2);
					test.checkAttendance(course3);
				}
				else
					System.out.println("Unrecognized input; returning to main menu.");
				break;
				
			case 5:
				System.out.println("Would you like the information to report here or sent to an email? Please enter H for here for E for email: ");
				String email = scan.next();
				System.out.println("Please enter the name of the course you would like to print, or enter ALL to print all courses:");
				for (int i = 0; i < 3; i++)
					System.out.println("Course ID: 0" + (i + 1) + " - Course Name: " + courses[i].getName() + " - Professor Name: " + courses[i].getInstructor().getName());
				input = scan.next();
				if (email.equals("H")) {
					if (input.equalsIgnoreCase("01"))
						course1.printEntireLog();
					else if (input.equalsIgnoreCase("02"))
						course2.printEntireLog();
					else if (input.equalsIgnoreCase("03"))
						course3.printEntireLog();
					else if (input.equalsIgnoreCase("ALL")) {
						course1.printEntireLog();
						course2.printEntireLog();
						course3.printEntireLog();
					}
					else
						System.out.println("Unrecognized input; returning to main menu.");
					break;
				}
				else {
					System.out.println("Please enter the email you would like the report sent to: ");
					String toSend = scan.next();
					System.out.println("Okay, the report was sent to " + toSend);
					break;
				}
				
			case 6:
				System.out.println("Would you like the information to report here or sent to an email? Please enter H for here for E for email: ");
				String toemail = scan.next();
				System.out.println("Please enter the date you would like to print: ");
				String date = scan.next();
				System.out.println("Please enter the name of the course you would like to print, or enter ALL to print all courses:");
				for (int i = 0; i < 3; i++)
					System.out.println("Course ID: 0" + (i + 1) + " - Course Name: " + courses[i].getName() + " - Professor Name: " + courses[i].getInstructor().getName());
				input = scan.next();
				if (toemail.equals("H")) {
					if (input.equalsIgnoreCase("01"))
						course1.printLogByDate(date);
					else if (input.equalsIgnoreCase("02"))
						course2.printLogByDate(date);
					else if (input.equalsIgnoreCase("03"))
						course3.printLogByDate(date);
					else if (input.equalsIgnoreCase("ALL")) {
						course1.printLogByDate(date);
						course2.printLogByDate(date);
						course3.printLogByDate(date);
					}
					else
						System.out.println("Unrecognized input; returning to main menu.");
					break;
				}
				else {
					System.out.println("Please enter the email you would like the report sent to: ");
					String toSend = scan.next();
					System.out.println("Okay, the report was sent to " + toSend);
					break;
				}
				
			case 7:
				cont = false;
				System.out.println("Bye!");
				break;
				
			default:
				System.out.println("Invalid Input");
			}
		}*/
	}
}
