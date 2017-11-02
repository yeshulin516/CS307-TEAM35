import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Instructor extends User {

	Connection con;

	int size = 10;
	private Course[] courses = new Course[size];
	int courseCount = 0;
	
	public Instructor(String aname, String auserId){
		super(aname, auserId);
	}
	
	public Course[] getCourses() {
		return courses;
	}
	
	public void addCourse(Course course) {
		if (courseCount < size - 1) {
			courses[courseCount] = course;
			courseCount ++;
			course.setInstructor(this);
		}
		else {
			courses = resize(size, courses);
			size *= 2;
			addCourse(course);
		}
	}
	
	public void printInfo() {
		System.out.println("User ID: " + this.getUserId());
		System.out.println("\tUser Name: " + this.getName());
		System.out.println("\t\tRegistered Courses:");
		for (int i = 0; i < courseCount; i++) {
			System.out.println("\t\t\t" + courses[i].getName());
		}
	}

	/* ADD INSTRUCTOR TO DATABASE */
	//returns
	public int addInstructorToDatabase ( String[] instructor ) {

		int val = 0;

		String query = "insert into Instructors values ('" + instructor[0] + "', '" + instructor[1] + "')";

		try {
			Statement stmt = con.createStatement();
			val = stmt.executeUpdate(query);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return val;
	}

	/* ADD COURSE TO DATABASE */
	//returns
	public int addCourseToDatabase ( String[] course ) {

		int val = 0;

		String query = "insert into Courses values ('" + course[0] + "', " + course[1] + ",'" + course[2] + "','" + course[3] + "','" + course[4] + "','" + course[5] + "', " + course[6] + ")";

		try {
			Statement stmt = con.createStatement();
			val = stmt.executeUpdate(query);

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return val;
	}


}
