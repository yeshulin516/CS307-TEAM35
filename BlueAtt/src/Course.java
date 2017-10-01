
public class Course {
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
}
