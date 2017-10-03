
public class User {
	private String name;
	private String userId;
	
	public User(String aname, String auserId){
		this.name = aname;
		this.userId = auserId;
	}
	
	public String getName() {
		return this.name;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public void setName(String sname) {
		this.name = sname;
	}
	
	public void setUserId(String id) {
		this.userId = id;
	}
	
	public Course[] resize(int size, Course[] courses) {
		Course[] scourses = new Course[size * 2];
		for (int i = 0; i < size; i++) {
			scourses[i] = courses[i];
		}
		return scourses;
	}
	
}
