/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Utils.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anselmus
 */
public class DatabaseTest {

    /**
     * Test of getSingleton method, of class Database.
     */
    @Test
    public void testGetSingleton() {
        System.out.println("getSingleton");
        Database expResult = null;
        Database result = Database.getSingleton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class Database.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        Database instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatement method, of class Database.
     */
    @Test
    public void testGetStatement() {
        System.out.println("getStatement");
        Database instance = null;
        Statement expResult = null;
        Statement result = instance.getStatement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuery method, of class Database.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String pQuery = "";
        Database instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.getQuery(pQuery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doQuery method, of class Database.
     */
    @Test
    public void testDoQuery() {
        System.out.println("doQuery");
        String pQuery = "";
        Database instance = null;
        long expResult = 0L;
        long result = instance.doQuery(pQuery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class Database.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        ResultSet pSet = null;
        Database instance = null;
        int expResult = 0;
        int result = instance.getRowCount(pSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
