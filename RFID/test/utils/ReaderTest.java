/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Utils.Reader;
import acs.jni.ACR120U;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anselmus
 */
public class ReaderTest {

    /**
     * Test of getNIM method, of class Reader.
     */
    @Test
    public void testGetNIM() {
        System.out.println("Testing Get NIM");
        ACR120U acr = new ACR120U();
        short s = acr.open((short)0);
        acr.status((short)0);
        System.out.println(acr.getFirmwareVersion());
        byte[] b1 = new byte[1];
        byte[] b2 = new byte[1];
        byte[] b3 = new byte[4];
        System.out.println(acr.select(s, b1, b2, b3));
        acr.select(s, b1, b2, b3);
        byte[] pkey = new byte[6];
        pkey[0]=0x11;
        pkey[1]=(byte)0xf1;
        pkey[2]=0x22;
        pkey[3]=(byte)0xf2;
        pkey[4]=0x33;
        pkey[5]=(byte)0xf3;
        byte keytype = (byte)acr.AC_MIFARE_LOGIN_KEYTYPE_A;
        byte sector = (byte)16;
        byte storeno = (byte)acr.AC_MIFARE_LOGIN_KEYTYPE_A;
        System.out.println("Hasil login : "+acr.login(s, sector, keytype, storeno, pkey));
    }
}
