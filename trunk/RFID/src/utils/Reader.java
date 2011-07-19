/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import acs.jni.ACR120U;

/**
 *
 * @author asus-K40IJ
 */
public class Reader {

    private static Reader mSingleton = new Reader();
    private ACR120U mACR = new ACR120U();
    private short mConnHandler;
    private final byte[] pKey = {
        (byte) 0x11, (byte) 0xF1,
        (byte) 0x22, (byte) 0xF3,
        (byte) 0x33, (byte) 0xF3
    };

    public static Reader getSingleton() {
        if (mSingleton == null) {
            mSingleton = new Reader();
        }
        return mSingleton;
    }

    public Reader() {
        try {
            System.load("./libs/ACR120UJNI.dll");
            System.load("./libs/ACR120U.dll");
        } catch (Exception ex) {
        }
        mConnHandler = mACR.open((short) 0);
    }

    public ACR120U getACR() {
        return mACR;
    }

    public String getNIM() {
        byte tSector = (byte) 16;
        String tReturn = null;
        return tReturn;
    }
}
