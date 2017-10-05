
public class Instructor extends User {
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
}
