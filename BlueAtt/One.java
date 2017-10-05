import java.io.*;
import java.util.*;
import java.lang.*;



public class One {
	
	

	//Select class from the UI
	public static void selectClassFromTheUI () {
		//dummy variavle
		String selected = "class3";	
		String [] classList = {"class1", "class2", "class3", "class4"};
		int length = classList.length;
		for (int i = 0; i< length; i++) {           
			if (selected.equals(classList[i])){
				System.out.println("Selected!");
			}
		}
	}

	//Pull class roster from the database
	public static void pullClassRosterFromDB () {
		//Connect to DB
		//dummy variavle
		String [] db = {"roster1", "rosrer2", "roster3", "roster4"};
		int length = db.length;
		ArrayList<String> stored = new ArrayList<String>();

		for (int i = 0; i< length; i++) {           
			stored.add(db[i]);  	
		}
		System.out.println("Stored Roster: ");
		for(int i = 0; i < stored.size(); i++) {   
			System.out.println(stored.get(i));
		}  
	}

	public static void WebApplicationInteractWithScanner () {
		//Libraries for scanners to start to work
		//check if getting the correct result
		//see if the request get correctly exexuted
		//dummy variable
		boolean result = false;
		boolean request = false;
		String bluetoothID = "167:2DB:HZ90";
		String returnedFromScanner = "167:2DB:HZ90";

		if (returnedFromScanner.equals(bluetoothID)){
			result = true;
		}
		request = true;
		if (request&&result){
			System.out.println("Scanner Interact with the Web Application successfully!");
		}
	}

	public static void ScanningSpeedCheck(){
		System.out.println("Function 11: ");
		long limit = 3000000;
		long startTime = System.nanoTime();
		System.out.println();
		System.out.println("Function 2: ");
		WebApplicationInteractWithScanner();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); //25467
		System.out.println(); 
		System.out.println("Function 3: ");
		if (duration < limit){
			System.out.println("Scanner works quickly enough!");
		}
	}

	public static String RouteToCorrectLocation()
	{
		System.out.println();
		System.out.println("Function 4: ");
		//dummy variable
		boolean route = false;
		String info = "option1";
		String correctLocation = "0x436578";
		String returnedLocation = "0x436578";
		if (correctLocation.equals(returnedLocation)){
			route = true;
		}
		if (route){
			return info;
		}
		return "Fail";
	}

	public static void OptionToReScan(){
		//dummy variable
		long duration = 5000000;
		long limit = 3000000;
		if (duration > limit){
			System.out.println("Rescan function called!");
		}

	}


	
	


	public static void addStuents(String t){
		ArrayList<String> list=new ArrayList<String>();

		list.add("a");
		list.add("b");  
		list.add("c");  
		list.add("d"); 
		if (!list.contains(t)){
			list.add(t);
			Arrays.toString(list.toArray());
			if (list.contains(t)){
				System.out.println("Add successfully!");
			}
			else {
				System.out.println("Fail to remove.");
			}
		}
		else {
			System.out.println("Student already exist.");
		}
	}
	public static void removdStudents(String t){
		ArrayList<String> list=new ArrayList<String>();

		list.add("a");
		list.add("b");  
		list.add("c");  
		list.add("d"); 
		if (list.contains(t)){
			list.remove(t);
			Arrays.toString(list.toArray());
			if (!list.contains(t)){
				System.out.println("Remove successfully!");
			}
			else {
				System.out.println("Fail to remove.");
			}
		}
		else {
			System.out.println("Student doesn't exist.");
		}
	}

	public static void editStudentsByDate(String data, String t, String action){

		ArrayList<String> dates=new ArrayList<String>();

		dates.add("1");
		dates.add("2");  
		dates.add("3");  
		dates.add("4");
		if (dates.contains(data)){ 
			ArrayList<String> list=new ArrayList<String>();

			list.add("a");
			list.add("b");  
			list.add("c");  
			list.add("d"); 
			if (list.contains(t)){
				if (action.equals("remove"))
				{
					list.remove(t);
				}
				
				Arrays.toString(list.toArray());
				if (!list.contains(t)){
					System.out.println("edit successfully!");
				}
				else {
					System.out.println("Fail to edit.");
				}
			}
			else {
				System.out.println("Student doesn't exist.");
			}

		}
		else {
			System.out.println("Dates don't exist.");
		}

	}
	public static void notifyDB(boolean db){
		if (db){
			System.out.println("Update database successfully!");
		}
		else{
			System.out.println("Fail to Update database!");
		}
	}

	public static void matchDeviceWithRoster (String deviceID){
		ArrayList<String> list=new ArrayList<String>();

		list.add("a");
		list.add("b");  
		list.add("c");  
		list.add("d"); 
		if (list.contains(deviceID)){
			System.out.println("Matched! Send query!");
		}
		else{
			System.out.println("Device is not registered.");
		}
		
	}

	public static void main(String []args) {
		System.out.println("Function 7: ");
		selectClassFromTheUI();




		System.out.println();
		System.out.println("Function 1: ");
		pullClassRosterFromDB();

		

		ScanningSpeedCheck();
		if (!RouteToCorrectLocation().equals("Fail")){
			System.out.println("Route successfully!");
		}
		System.out.println();
		System.out.println("Function 5: ");
		OptionToReScan(); 

		System.out.println("Function 6: ");
		String t1 = "a";
		String t2 = "b";
		addStuents(t1);
		removdStudents(t2);

		System.out.println("Function 8: ");
		editStudentsByDate("1", t1, "remove");
		System.out.println("Function 9: ");
		notifyDB(true);
		System.out.println("Function 10: ");
		matchDeviceWithRoster(t1);


	}

}
