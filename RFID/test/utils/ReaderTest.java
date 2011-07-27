/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import Utils.Reader;
import java.io.File;
import org.junit.Test;

/**
 *
 * @author hyouda
 */
public class ReaderTest {

    /**
     * Test of getNX method, of class Reader.
     */
    @Test
    public void testGetNX() {
        System.out.println("NIM : " + Reader.getSingleton().getNX(16,1));
    }

    @Test
    public void testGetIE() {
        System.out.println("IE : " + Reader.getSingleton().getIE(16,1));
    }

    @Test
    public void testGetMP() {
        System.out.println("MP : " + Reader.getSingleton().getMP(16,1));
    }
}