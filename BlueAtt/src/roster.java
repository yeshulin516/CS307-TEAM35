/**
 * Created by justin on 9/26/17.
 */

import java.util.*;
import java.io.*;
import java.sql.*;


public class roster {

    Connection con;

    public roster() {
        try {
            Class.forName( "oracle.jdbc.driver.OracleDriver" );
        }
        catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        try {
            con =
                    DriverManager.getConnection( "jdbc:oracle:thin:@claros.cs.purdue.edu:1524:strep","blueatt", "dfa823n92a" );
        }
        catch ( SQLException e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line;
        int lines = 0;
        int numStudents = 0;
        int numInstructors = 0;
        int numCourses = 0;
        int numRecords = 0;
        int numDevices = 0;

        //string matrix to store file's input
        String[][] students = new String[0][0];
        String[][] instructors = new String[0][0];
        String[][] courses = new String[0][0];
        String[][] records = new String[0][0];
        String[][] devices = new String[0][0];

        //READ STUDENTS FILE
        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/students.csv"));

            //read how many lines are in .csv file
            lines = 0;
            while ((line = br.readLine()) != null) {lines++;}
            br.close();
            br = new BufferedReader(new FileReader("src/students.csv"));

            //string matrix with row same as file
            students = new String[lines][2];

            numStudents = lines;

            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is name
                students[i][0] = line.split(",")[0];
                //second value is username
                students[i][1] = line.split(",")[1];
                i++;
            }

            br.close();
        }
        catch (FileNotFoundException exp) {
            System.out.println("File Not Found!");
        }
        catch (IOException ex) {
            System.out.println("IO Error!");
        }


        roster mdb = new roster();
        int val = 0;

        //READ INSTRUCTORS FILE
        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/instructors.csv"));

            //read how many lines are in .csv file
            lines = 0;
            while ((line = br.readLine()) != null) {lines++;}
            br.close();
            br = new BufferedReader(new FileReader("src/instructors.csv"));

            //string matrix with row same as file
            instructors = new String[lines][2];

            numInstructors = lines;

            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is name
                instructors[i][0] = line.split(",")[0];
                //second value is username
                instructors[i][1] = line.split(",")[1];
                i++;
            }

            br.close();
        }
        catch (FileNotFoundException exp) {
            System.out.println("File Not Found!");
        }
        catch (IOException ex) {
            System.out.println("IO Error!");
        }

        //READ COURSES FILE
        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/courses.csv"));

            //read how many lines are in .csv file
            lines = 0;
            while ((line = br.readLine()) != null) {lines++;}
            br.close();
            br = new BufferedReader(new FileReader("src/courses.csv"));

            //string matrix with row same as file
            courses = new String[lines][7];

            numCourses = lines;

            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is  title
                courses[i][0] = line.split(",")[0];
                //second value is ID
                courses[i][1] = line.split(",")[1];
                //third value is instructor username
                courses[i][2] = line.split(",")[2];
                //fourth value is day of week
                courses[i][3] = line.split(",")[3];
                //fifth value is start day and time
                courses[i][4] = line.split(",")[4];
                //sixth value is end day and time
                courses[i][5] = line.split(",")[5];
                //seventh value is size
                courses[i][6] = line.split(",")[6];

                i++;
            }

            br.close();
        }
        catch (FileNotFoundException exp) {
            System.out.println("File Not Found!");
        }
        catch (IOException ex) {
            System.out.println("IO Error!");
        }

        //READ RECORDS FILE
        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/records.csv"));

            //read how many lines are in .csv file
            lines = 0;
            while ((line = br.readLine()) != null) {lines++;}
            br.close();
            br = new BufferedReader(new FileReader("src/records.csv"));

            //string matrix with row same as file
            records = new String[lines][4];

            numRecords = lines;

            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is username
                records[i][0] = line.split(",")[0];
                //second value is courseID
                records[i][1] = line.split(",")[1];
                //third value is record date
                records[i][2] = line.split(",")[2];
                //fourth value is attendance value
                records[i][3] = line.split(",")[3];
                i++;
            }

            br.close();
        }
        catch (FileNotFoundException exp) {
            System.out.println("File Not Found!");
        }
        catch (IOException ex) {
            System.out.println("IO Error!");
        }

        //READ DEVICES FILE
        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/devices.csv"));

            //read how many lines are in .csv file
            lines = 0;
            while ((line = br.readLine()) != null) {lines++;}
            br.close();

            br = new BufferedReader(new FileReader("src/devices.csv"));

            //string matrix with row same as file
            devices = new String[lines][2];

            numDevices = lines;


            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is username
                devices[i][0] = line.split(",")[0];
                //second value is deviceID
                devices[i][1] = line.split(",")[1];
                i++;
            }

            br.close();
        }
        catch (FileNotFoundException exp) {
            System.out.println("File Not Found!");
        }
        catch (IOException ex) {
            System.out.println("IO Error!");
        }

        val = 0;
        String[] temp = new String[35];

        //loop through all students and add to database
        for (int j = 0; j < numStudents; j++) {
            val = mdb.addStudentToDatabase(students[j]);
        }

        //loop through all instructors and add to database
        for (int j = 0; j < numInstructors; j++) {
            val = mdb.addInstructorToDatabase(instructors[j]);
        }

        //loop through all courses and add to database
        for (int j = 0; j < numCourses; j++) {
            val = mdb.addCourseToDatabase(courses[j]);
        }

        //loop through all devices and add to database
        for (int j = 0; j < numDevices; j++) {
            val = mdb.addDeviceToDatabase(devices[j]);
        }

        String[] n = new String[1];
        //loop through all records and add to database
        for (int j = 0; j < numRecords; j++) {
            n[0] = records[j][0];
            val = mdb.addStudentToCourse(n, 307);
        }


        ArrayList<String> array = new ArrayList<>();
        String string;

        mdb.printArrayList(mdb.pullStudentTable(), 2);
        mdb.printArrayList(mdb.pullDevicesTable(), 2);
        mdb.printArrayList(mdb.pullInstructorTable(), 2);
        mdb.printArrayList(mdb.pullCourseTable(), 7);
        mdb.printArrayList(mdb.pullRecordTable(), 4);

        //add attendance  record for a student based on device ID present
        //add device ID to the database
        //add another device to the database
        //update a device in the database
        //get attendance record from a class for a specific student
        boolean cont = true;
        while (cont == true) {
            System.out.println(
                    "1. add attendance  record for a student based on device ID present\n"
                    + "2. add device ID to the database\n"
                    + "3. add another device to the database\n"
                    + "4. update a device in the database\n"
                    + "5. get attendance record from a class for a specific student\n"
            );
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    testAddAttendance();
                    break;
                case 2:
                    testAddDevice();
                    break;
                case 3:
                    testAddDevice();
                    break;
                case 4:
                    testUpdateDevice();
                    break;
                case 5:
                    testGetAttendance();
                    break;
                default:
                    System.out.println("Invalid Input");
            }

        }



/*
        while (cont == true) {
            System.out.println("1. pullStudentSpecificDateAttendance\n"
                    + "2. pullCourseSemesterAttendance\n"
                    + "3. pullCourseDeviceIDs\n"
                    + "4. pullRosterFromCourse\n"
                    + "5. updateAttendenceOnSpecificDate\n"
                    + "6. removeStudentFromCourse\n"
                    + "7. pullStudentSemesterAttendance\n"
                    + "8. pullCourseSpecificDateAttendance\n"
                    + "9. addRecordToDatabase\n");
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    string = mdb.pullStudentSpecificDateAttendance("justin5", 307, "10-OCT-17");
                    System.out.println(string);
                    break;
                case 2:
                    array = mdb.pullCourseSemesterAttendance("justin5", 307);
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 3:
                    array = mdb.pullCourseDeviceIDs(307);
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 4:
                    array = mdb.pullRosterFromCourse(307);
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 5:
                    mdb.updateAttendenceOnSpecificDate("justin5", 307, "10-OCT-17", "Y");
                    array = mdb.pullRecordTable();
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 6:
                    mdb.removeStudentFromCourse("justin5", 307);
                    array = mdb.pullStudentTable();
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 7:
                    array = mdb.pullStudentSemesterAttendance("justin5", 307);
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 8:
                    array = mdb.pullCourseSpecificDateAttendance(307, "1-JAN-00");
                    System.out.println(array.toString());
                    System.out.println("");
                    break;
                case 9:
                    mdb.addRecordToDatabase("justin5", 307, "10-OCT-17", "N");
                    array = mdb.pullRecordTable();
                    System.out.println(array.toString());
                    System.out.println("");
                default:
                    System.out.println("Invalid Input");
            }
        }
        */



    }

    public static void printArrayList( ArrayList<String> array, int rowSize ) {

        int j = 0;
        for (int i = 0; i < array.size(); i++) {

            System.out.println(array.get(i));

            if (j == rowSize-1) {
                j = 0;
                System.out.println("");
                continue;
            }
            j++;
        }

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

    /* ADD DEVICE TO DATABASE */
    //returns
    public int addDeviceToDatabase ( String[] device ) {

        int val = 0;

        String query = "insert into Devices values ('" + device[0] + "', '" + device[1] + "')";

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

    /* ADD STUDENT TO DATABASE */
    //returns
    public int addStudentToDatabase ( String[] student ) {

        int val = 0;

        String query = "insert into Students values ('" + student[0] + "', '" + student[1] + "')";

        try {
            Statement stmt = con.createStatement();
            val = stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    /* ADD RECORD TO DATABASE */
    //returns
    public int addRecordToDatabase ( String studentUsername, int courseID, String recordDate, String attendance ) {

        int val = 0;

        String query = "insert into Records values ('" + studentUsername + "', " + courseID + ", '" + recordDate + "', '" + attendance + "')";

        try {
            Statement stmt = con.createStatement();
            val = stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    /* PULL STUDENT'S USERNAME USING DEVICE ID */
    //returns username string
    public String pullStudentUsernameUsingDeviceID (String deviceID) {


        String query = "select studentUsername from Devices where deviceID = '" + deviceID + "'";

        String val = "NULL";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                val = rs.getString( "studentUsername" );
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    /* PULL STUDENT'S ATTENDANCE ON A SPECIFIC DATE */
    //returns String of 'Y' or 'N' or "NULL"
    public String pullStudentSpecificDateAttendance ( String studentUsername, int courseID, String date) {

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

    /* PULL STUDENT'S ATTENDANCE FOR THE WHOLE SEMESTER */
    //returns ArrayList of attendance record, each cell is 'Y' or 'N'
    public ArrayList<String> pullStudentSemesterAttendance ( String studentUsername, int courseID ) {

        ArrayList<String> attRecord = new ArrayList<>();

        String query = "select attendance from Records where studentUsername = '" + studentUsername + "' and courseID = " + courseID + " and recordDate > '1-JAN-00'";

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

    /* PULL STUDENTS' DEVICE IDs FROM COURSE ROSTER */
    //returns ArrayList of device IDs
    public ArrayList<String> pullCourseDeviceIDs ( int courseID ) {

        ArrayList<String> deviceIDs = new ArrayList<>();

        String query = "select deviceID from Devices natural join Students natural join Records where recordDate = '1-JAN-00' and courseID = '" + courseID + "'";

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

    /* UPDATE STUDENT'S DEVICE */
    //returns
    public int updateDevice (String studentUsername, String oldDeviceID, String newDeviceID) {

        int val = 0;

        String query = "update Devices set deviceID = '" + newDeviceID +  "' where studentUsername = '" + studentUsername + "' and deviceID = '" + oldDeviceID + "'";

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

    /* GET INSTRUCTOR TABLE */
    public ArrayList<String> pullInstructorTable ( ) {

        ArrayList<String> array = new ArrayList<>();

        String query = "select * from Instructors";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                array.add(rs.getString( "instructorName" ));
                array.add(rs.getString( "instructorUserName" ));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }

    /* GET STUDENT TABLE */
    public ArrayList<String> pullStudentTable ( ) {

        ArrayList<String> array = new ArrayList<>();

        String query = "select * from Students";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                array.add(rs.getString( "studentName" ));
                array.add(rs.getString( "studentUsername" ));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }

    /* GET COURSE TABLE */
    public ArrayList<String> pullCourseTable ( ) {

        ArrayList<String> array = new ArrayList<>();

        String query = "select * from Courses";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                array.add(rs.getString( "title" ));
                array.add(rs.getString( "courseID" ));
                array.add(rs.getString( "instructorUsername" ));
                array.add(rs.getString( "courseDay" ));
                array.add(rs.getString( "courseStartTime" ));
                array.add(rs.getString( "courseEndTime" ));
                array.add(rs.getString( "courseSize" ));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }

    /* GET RECORD TABLE */
    public ArrayList<String> pullRecordTable ( ) {

        ArrayList<String> array = new ArrayList<>();

        String query = "select * from Records";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                array.add(rs.getString( "studentUsername" ));
                array.add(rs.getString( "courseID" ));
                array.add(rs.getString( "recordDate" ));
                array.add(rs.getString( "attendance" ));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }

    /* GET DEVICES TABLE */
    public ArrayList<String> pullDevicesTable ( ) {

        ArrayList<String> array = new ArrayList<>();

        String query = "select * from Devices";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                array.add(rs.getString( "studentUsername" ));
                array.add(rs.getString( "deviceID" ));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }


    //add attendance record for a student based on device ID present
    public static void testAddAttendance ( ) {
        roster mdb = new roster();
        printArrayList(mdb.pullRecordTable(), 4);

        Scanner sc = new Scanner(System.in);
        String[] test = new String[3];

        System.out.println("Enter student's device ID: ");
        test[0] = sc.nextLine();
        System.out.println("Enter date (DD-MTH-YY): ");
        test[1] = sc.nextLine();
        System.out.println("Attendance (Y, N): ");
        test[2] = sc.nextLine();
        System.out.println("Enter course ID: ");
        int courseID = sc.nextInt();


        String username = mdb.pullStudentUsernameUsingDeviceID(test[0]);

        mdb.addRecordToDatabase(username, courseID, test[1], test[2]);
        printArrayList(mdb.pullRecordTable(), 4);


    }

    //add device ID to the database
    public static void testAddDevice () {
        roster mdb = new roster();
        printArrayList(mdb.pullDevicesTable(), 2);

        Scanner sc = new Scanner(System.in);
        String[] test = new String[2];

        System.out.println("Enter student's username: ");
        test[0] = sc.nextLine();
        System.out.println("Enter student's device ID: ");
        test[1] = sc.nextLine();

        mdb.addDeviceToDatabase(test);
        printArrayList(mdb.pullDevicesTable(), 2);
    }

    //update a device in the database
    public static void testUpdateDevice ( ) {
        roster mdb = new roster();
        printArrayList(mdb.pullDevicesTable(), 2);

        Scanner sc = new Scanner(System.in);
        String[] test = new String[3];

        System.out.println("Enter student's username: ");
        test[0] = sc.nextLine();
        System.out.println("Enter student's old device ID: ");
        test[1] = sc.nextLine();
        System.out.println("Enter student's new device ID: ");
        test[2] = sc.nextLine();

        mdb.updateDevice(test[0], test[1], test[2]);
        printArrayList(mdb.pullDevicesTable(), 2);

    }

    //get attendance record from a class for a specific student
    public static void testGetAttendance ( ) {
        roster mdb = new roster();

        //add a unique record to the table to pull
        mdb.addRecordToDatabase("justin5", 307, "10-OCT-17", "Y");
        mdb.addRecordToDatabase("justin5", 307, "12-OCT-17", "Y");
        mdb.addRecordToDatabase("justin5", 307, "14-OCT-17", "N");

        printArrayList(mdb.pullRecordTable(), 4);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter student's username: ");
        String zero = sc.nextLine();
        System.out.println("Enter course id: ");
        int one = sc.nextInt();

        printArrayList(mdb.pullStudentSemesterAttendance(zero, one), 1);
    }
}