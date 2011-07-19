/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Utils.Helper;
import Utils.User;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus-K40IJ
 */
public class Testing {
    public static void main(String[] args) {
        try {
            ArrayList<User> tResult = Helper.parseCSV("./others/FMIPA.csv");
            for (User user : tResult) {
                System.out.println("Username : " + user.getmUsername());
                System.out.println("Password : " + user.getmPassword());
                System.out.println("Firstname : " + user.getmFirstname());
                System.out.println("Lastname : " + user.getmLastname());
                System.out.println("Email : " + user.getmEmail());
                System.out.println("Course : " + user.getmCourse());
                System.out.println("Role : " + user.getmRole());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
