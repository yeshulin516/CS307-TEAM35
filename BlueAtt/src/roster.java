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
        String[][] students = new String[5][3];

        try {
            //read file into buffer
            BufferedReader br = new BufferedReader(new FileReader("src/students.csv"));

            int i = 0;

            //loop through each line parsing based on commas
            while ((line = br.readLine()) != null) {

                students[i][0] = line.split(",")[0];
                students[i][1] = line.split(",")[1];
                students[i][2] = line.split(",")[2];

                i++;
            }
        }
        catch (FileNotFoundException exp) {
            System.out.println("Error 1");
        }
        catch (IOException ex) {
            System.out.println("Error 2");
        }

        String temp[] = new String[3];
        temp[0] = students[0][1];
        temp[1] = students[1][1];
        temp[2] = students[2][1];

        roster mdb = new roster();


        ArrayList<String> array = new ArrayList<>();

        //mdb.addStudentToCourse(temp,307);
        //mdb.pullSpecificDateAttendance("boudrej", 307, "1-JAN-00");
        //array = mdb.pullSemesterAttendance("boudrej", 307);
        //array = mdb.pullRosterDeviceIDs(307);
        //mdb.updateAttendenceOnSpecificDate("boudrej", 307, "3-OCT-17",  "Y");
        mdb.removeStudentFromCourse("boudrej", 408);

        System.out.println(array.toString());

    }

    /* ADD STUDENTS TO RECORDS TABLE LINK STUDENT WITH COURSE */
    public void addStudentToCourse ( String[] studentUsernames, int courseID ) {

        for (int i = 0; i < studentUsernames.length; i++) {

            //Adds a pre determined date to recognize initialized record
            String query = "insert into Records values ('" + studentUsernames[i] + "', " + courseID + ", '1-JAN-00', 'N')";

            try {
                Statement stmt = con.createStatement();
                int rs = stmt.executeUpdate(query);

                System.out.println(rs);

                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    /* PULL STUDENTS' DEVICE IDs FROM CLASS ROSTER */
    //returns ArrayList of device IDs
    public ArrayList<String> pullRosterDeviceIDs ( int courseID ) {

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
    public void updateAttendenceOnSpecificDate (String studentUsername, int courseID, String date, String attendanceVal) {

        String query = "update Records set attendance = '" + attendanceVal + "' where studentUsername = '" + studentUsername + "' and courseID = " + courseID + " and recordDate = '" +  date + "'";

        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);

            System.out.println(rs);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* REMOVE ALL RECORDS FOR A STUDENT FROM A COURSE */
    public void removeStudentFromCourse (String studentUsername, int coureseID) {

        String query = "delete from Records where studentUsername = '" + studentUsername + "' and courseID = " + coureseID;

        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);

            System.out.println(rs);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
