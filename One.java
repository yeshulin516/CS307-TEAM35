import java.io.*;
import java.util.*;




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



	 public static void main(String []args) {
    	//selectClassFromTheUI();





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
	}

}
