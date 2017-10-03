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
            //in = new FileInputStream("students.csv");
            BufferedReader br = new BufferedReader(new FileReader("src/students.txt"));

            int i = 0;

            while ((line = br.readLine()) != null) {

                students[i][0] = line.split(",")[0];
                students[i][1] = line.split(",")[1];
                students[i][2] = line.split(",")[2];

                System.out.print(students[i][0] + " " + students[i][1] + " " + students[i][2] + "\n");
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
