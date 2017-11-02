import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Student extends User {

	Connection con;

	int size = 10;
	private Course[] courses = new Course[size];
	int courseCount = 0;
	
	public Student(String aname, String auserId) {
		super(aname, auserId);
	}
	
	public Course[] getCourses() {
		return courses;
	}
	
	public void addCourse(Course course) {
		if (courseCount < size - 1) {
			courses[courseCount] = course;
			courseCount ++;
			course.addStudent(this);
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

	/* ADD STUDENT TO DATABASE */
	//returns
	public int addStudentToDatabase ( String[] student ) {

		int val = 0;

		String query = "insert into Students values ('" + student[0] + "', '" + student[1] + "', '" + student[2] + "')";

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
