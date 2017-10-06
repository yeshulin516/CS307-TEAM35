import java.lang.*;
import java.util.*;
public class Test {
	public static void main(String[] args) {

		Student stu1 = new Student("Student 1", "01");
		Student stu2 = new Student("Student 2", "02");
		Student stu3 = new Student("Student 3", "03");
		
		Instructor ins1 = new Instructor("Instructor 1", "11");
		Instructor ins2 = new Instructor("Instructor 2", "12");
		Instructor ins3 = new Instructor("Instructor 3", "13");
		
		Course course1 = new Course("Course 1", ins1);
		Course course2 = new Course("Course 2", ins2);
		Course course3 = new Course("Course 3", ins3);

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
		
		Scanner scan = new Scanner(System.in);
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
				stu1.printInfo();
				stu2.printInfo();
				stu3.printInfo();
				break;
			case 2:
				ins1.printInfo();
				ins2.printInfo();
				ins3.printInfo();
				break;
			case 3:

				ArrayList <String> courses = new ArrayList<String>();
				courses.add("course1");
				courses.add("course2");
				courses.add("course3");
				System.out.println("Select from: ");
				System.out.println(Arrays.toString(courses.toArray()));
				String courseName = scan.next();
				switch (courseName){
					case "course1":
						course1.printInfo();
						break;
					case "course2":
						course2.printInfo();
						break;
					case "course3":
						course3.printInfo();
						break;
					case "exit":
						break;
				}
				break;
			case 4:
				Bluetooth test = new Bluetooth();
				test.checkAttendance(course1);
				test.checkAttendance(course2);
				test.checkAttendance(course3);
				break;
			case 5:
				course1.printEntireLog();
				course2.printEntireLog();
				course3.printEntireLog();
				break;
			case 6:
				System.out.println("Please enter the date you would like to print: ");
				String date = scan.next();
				course1.printLogByDate(date);
				course2.printLogByDate(date);
				course3.printLogByDate(date);
				break;
			case 7:
				cont = false;
				System.out.println("Bye!");
			default:
				System.out.println("Invalid Input");
			}
		}
	}
}
