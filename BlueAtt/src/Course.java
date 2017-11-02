import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {

	Connection con;

	private String courseName;
	private Instructor instructor;
	int size = 100;
	private Student[] roster = new Student[size];
	int studentCount = 0;
	int absCount = 0;
	CourseLogEntry[] log = new CourseLogEntry[365];
	int logCount = 0;
	
	public Course(String name, Instructor ainstructor){
		this.courseName = name;
		this.instructor = ainstructor;
	}
	
	public String getName() {
		return this.courseName;
	}
	
	public void setName(String name) {
		this.courseName = name;
	}
	
	public Instructor getInstructor() {
		return this.instructor;
	}
	
	public void setInstructor(Instructor inst){
		this.instructor = inst;
	}
	public Student[] getRoster() {
		return roster;
	}
	
	public int getAbsCount() {
		return absCount;
	}
	
	public void setAbsCount(int count) {
		this.absCount = count;
	}
	
	public void addStudent(Student student){
		if (studentCount < size - 1) {
			roster[studentCount] = student;
			studentCount ++;
		}
		else {
			Student[] sroster = new Student[size * 2];
			for (int i = 0; i < size; i++) {
				sroster[i] = roster[i];
			}
			this.roster = sroster;
			size *= 2;
			addStudent(student);
		}
	}
	
	public void printInfo() {
		System.out.println("Course Name: " + this.getName());
		System.out.println("\tInstructor Name: " + this.getInstructor().getName());
		System.out.println("\t\tRegistered Students:");
		for (int i = 0; i < studentCount; i++) {
			System.out.println("\t\t\t" + roster[i].getName());
		}
	}
	
	public boolean inRoster(String id){
		for (int i = 0; i < studentCount; i ++){
			if (id.equals(roster[i].getUserId()))
				return true;
		}
		return false;
	}
	
	public String[] getAbsent(String[] attended, int count) {
		String[] absent = new String[1000];
		absCount = 0;
		if (count == 0) {
			for (int i = 0; i < studentCount; i ++) {
				absent[i] = roster[i].getUserId();
				absCount = studentCount;
			}
			return absent;
		}	
		boolean contained = false;
		for (int i = 0; i < studentCount; i ++) {
			contained = false;
			for (int y = 0; y < count; y ++) {
				if (roster[i].getUserId().equals(attended[y])) {
					contained = true;
					break;
				}
			}
			if (contained == false) {
				absent[absCount] = roster[i].getUserId();
				absCount ++;
			}
		}
		return absent;
	}

	public void addLogEntry(String date, Student[] attended, Student[] absent, int numAttended, int numAbsent) {
		CourseLogEntry toAdd = new CourseLogEntry(date, attended, absent, numAttended, numAbsent);
		log[logCount] = toAdd;
		logCount ++;
	}

	public Student[] idToObj(String[] ids, int count ) {
		Student[] toRet = new Student[1000];
		int toRetCount = 0;
		for (int i = 0; i < count; i ++) {
			for (int y = 0; y < studentCount; y ++) {
				if (ids[i].equals(roster[y].getUserId())) {
					toRet[toRetCount] = roster[y];
					toRetCount ++;
				}
			}
		}
		return toRet;
	}

	public void printEntireLog(){
		System.out.println("Printing Attendance Log for " + this.getName() + ", taught by " + this.getInstructor().getName() + "...");
		for (int i = 0; i < logCount; i ++) {
			System.out.println("\tAttendance for date " + log[i].date + ":");
			log[i].printAttended();
			log[i].printAbsent();
		}
	}
	
	public void printLogByDate(String date){
		System.out.println("Printing Attendance Log for " + this.getName() + ", taught by " + this.getInstructor().getName() + "...");
		for (int i = 0; i < logCount; i ++) {
			if (date.equals(log[i].date)) {
				System.out.println("\tAttendance for date " + log[i].date + ":");
				log[i].printAttended();
				log[i].printAbsent();
				return;
			}
		}
		System.out.println("Date not found in log");
	}

	/* ADD STUDENTS TO RECORDS TABLE LINK STUDENT WITH COURSE */
	//returns
	public int addStudentToCourse ( String[] studentUsernames, int courseID ) {

		int val = 0;

		for (int i = 0; i < studentUsernames.length; i++) {

			//Adds a pre determined date to recognize initialized record
			String query = "insert into Records values ('" + studentUsernames[i] + "', " + courseID + ", '1-JAN-00', 'N')";

			try {
				Statement stmt = con.createStatement();
				int rs = stmt.executeUpdate(query);

				val = rs;

				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return val;
	}

	/* PULL STUDENT'S ATTENDANCE ON A SPECIFIC DATE */
	//returns String of 'Y' or 'N' or "NULL"
	public String pullSpecificDateAttendance ( String studentUsername, int courseID, String date) {

		String record = "NULL";

		String query = "select attendance from Records where studentUsername = '" + studentUsername + "' and courseID = " + courseID + " and recordDate = '" + date + "'";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while ( rs.next() ) {
				record = rs.getString( "attendance" );
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return record;
	}

	/* PULL ROSTER FROM A COURSE */
	//returns ArrayList of student usernames in course
	public ArrayList<String> pullRosterFromCourse (int courseID) {

		ArrayList<String> usernames = new ArrayList<>();

		String query = "select studentUsername from Students natural join Records where recordDate = '1-JAN-00' and courseID = " + courseID;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while ( rs.next() ) {
				usernames.add(rs.getString( "studentUsername" ));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usernames;

	}

	/* PULL COURSE'S ATTENDANCE ON A SPECIFIC DATE */
	//returns ArrayList of attendance record, each cell is 'Y' or 'N'
	public ArrayList<String> pullCourseSpecificDateAttendance ( int courseID, String date ) {

		ArrayList<String> attRecord = new ArrayList<>();

		String query = "select attendance from Records where courseID = " + courseID + " and recordDate = '" + date + "'";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while ( rs.next() ) {
				attRecord.add(rs.getString( "attendance" ));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attRecord;
	}

	/* PULL COURSE'S ATTENDANCE FOR THE WHOLE SEMESTER */
	//returns ArrayList of attendance record, each cell is 'Y' or 'N'
	public ArrayList<String> pullCourseSemesterAttendance ( String studentUsername, int courseID ) {

		ArrayList<String> attRecord = new ArrayList<>();

		String query = "select attendance from Records where courseID = " + courseID + " and recordDate > '1-JAN-00'";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while ( rs.next() ) {
				attRecord.add(rs.getString( "attendance" ));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attRecord;
	}

	/* PULL STUDENTS' DEVICE IDs FROM COURSE ROSTER */
	//returns ArrayList of device IDs
	public ArrayList<String> pullCourseDeviceIDs ( int courseID ) {

		ArrayList<String> deviceIDs = new ArrayList<>();

		String query = "select deviceID from Students natural join Records where recordDate = '1-JAN-00' and courseID = " + courseID;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while ( rs.next() ) {
				deviceIDs.add(rs.getString( "deviceID" ));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deviceIDs;
	}

	/* UPDATE STUDENT'S ATTENDANCE ON A SPECIFIC DATE */
	//returns
	public int updateAttendenceOnSpecificDate (String studentUsername, int courseID, String date, String attendanceVal) {

		int val = 0;

		String query = "update Records set attendance = '" + attendanceVal + "' where studentUsername = '" + studentUsername + "' and courseID = " + courseID + " and recordDate = '" +  date + "'";

		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);

			val = rs;

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return val;
	}

	/* REMOVE STUDENT AND ALL RECORDS FROM A COURSE */
	public int removeStudentFromCourse (String studentUsername, int coureseID) {

		int val = 0;

		String query = "delete from Records where studentUsername = '" + studentUsername + "' and courseID = " + coureseID;

		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(query);

			val = rs;

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return val;

	}
}
