/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author asus-K40IJ
 */
public class Helper {
    
    public static ArrayList<User> parseCSV(String pPathFile) throws FileNotFoundException {
        ArrayList<User> tReturn = new ArrayList<User>();
        File tFile = new File(pPathFile);
        Scanner tScanner = new Scanner(tFile);
        while (tScanner.hasNext()) {
            String[] tRead = tScanner.nextLine().split(";");
            User tTemp = new User(
                    tRead[0], 
                    tRead[1], 
                    tRead[2], 
                    tRead[3],
                    tRead[4], 
                    tRead[5], 
                    tRead[6]);
            tReturn.add(tTemp);
        }
        return tReturn;
    }
    
}
