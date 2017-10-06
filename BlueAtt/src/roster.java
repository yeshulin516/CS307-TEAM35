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

        String line;
        int lines = 0;
        //string matrix to store file's input
        String[][] students = new String[0][0];
        String[][] instructors = new String[0][0];
        String[][] courses = new String[0][0];


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
            students = new String[lines][3];

            int i = 0;
            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {
                //first value is name
                students[i][0] = line.split(",")[0];
                //second value is username
                students[i][1] = line.split(",")[1];
                //third value is bluetooth ID
                students[i][2] = line.split(",")[2];
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

        //loop through all students and add to database
        for (int j = 0; j < lines; j++) {
            val = mdb.addStudentToDatabase(students[j]);

            System.out.println(val);
        }


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

        val = 0;

        //loop through all instructos and add to database
        for (int j = 0; j < lines; j++) {
            val = mdb.addInstructorToDatabase(instructors[j]);

            System.out.println(val);
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

        val = 0;

        //loop through all courses and add to database
        for (int j = 0; j < lines; j++) {
            val = mdb.addCourseToDatabase(courses[j]);

            System.out.println(val);
        }


        //ArrayList<String> array = new ArrayList<>();

        //mdb.addStudentToCourse(temp,307);
        //mdb.pullSpecificDateAttendance("boudrej", 307, "1-JAN-00");
        //array = mdb.pullSemesterAttendance("boudrej", 307);
        //array = mdb.pullRosterDeviceIDs(307);
        //array = mdb.pullRosterFromCourse(307);
        //mdb.updateAttendenceOnSpecificDate("boudrej", 307, "3-OCT-17",  "Y");
        //mdb.removeStudentFromCourse("boudrej", 408);


        //System.out.println(array.toString());

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

        return val;    }

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

    /* PULL STUDENT'S ATTENDANCE FOR THE WHOLE SEMESTER */
    //returns ArrayList of attendance record, each cell is 'Y' or 'N'
    public ArrayList<String> pullSemesterAttendance ( String studentUsername, int courseID ) {

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

    /* REMOVE ALL RECORDS FOR A STUDENT FROM A COURSE */
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
