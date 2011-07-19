/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Utils.Helper;
import Utils.User;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author Anselmus
 */
public class HelperTest {

    /**
     * Test of parseCSV method, of class Helper.
     */
    @Test
    public void testParseCSV() throws Exception {
        System.out.println("Testing Parsing CSV to Array");
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
    }
}
