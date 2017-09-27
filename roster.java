/**
 * Created by justin on 9/26/17.
 */

import java.util.*;
import java.io.*;


public class roster {

    public static void main(String[] args) {

        String line;
        String[][] students = new String[5][3];

        try {
            //Create reader to read lines of file
            BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));

            int i = 0;

            //loop through file by line
            while ((line = br.readLine()) != null) {

                //name
                students[i][0] = line.split(",")[0];
                //username
                students[i][1] = line.split(",")[1];
                //is bluetooth ID
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

    }


}
