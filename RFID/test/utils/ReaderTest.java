/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import Utils.Reader;
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
        System.out.println("NIM : " + Reader.getSingleton().getNX());
    }

    @Test
    public void testGetIE() {
        System.out.println("IE : " + Reader.getSingleton().getIE());
    }

    @Test
    public void testGetMP() {
        System.out.println("MP : " + Reader.getSingleton().getMP());
    }
}