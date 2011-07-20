/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import acs.jni.ACR120U;
import java.util.Arrays;

/**
 *
 * @author asus-K40IJ
 */
public class Reader {

    private static Reader mSingleton = new Reader();
    private ACR120U mACR = new ACR120U();
    private byte[] mSerial = new byte[4];
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
            System.out.println("Cannot load library : " + ex.getMessage());
        }
        mConnHandler = mACR.open((short) 0);
    }

    private short doSelect() {
        byte[] tTagType = new byte[1];
        byte[] tTagLenght = new byte[1];
        return mACR.select(mConnHandler, tTagType, tTagLenght, mSerial);
    }

    private String doDecrypt(String pChiperText, String pKey) {
        if (pKey.length() >= 8) {
            StringBuilder tBuilder = new StringBuilder();
            byte[] tTemp = new byte[3];
            tTemp[0] = (byte) pKey.charAt(1);
            tTemp[1] = (byte) pKey.charAt(3);
            tTemp[2] = (byte) pKey.charAt(4);
            int j = 0;
            for (int i = 0; i < pChiperText.length(); i++) {
                tBuilder.append((byte) pChiperText.charAt(i) ^ tTemp[j]);
                if (j < 3) {
                    j++;
                } else {
                    j = 1;
                }
            }
            return tBuilder.toString();
        } else {
            return null;
        }
    }

    public ACR120U getACR() {
        return mACR;
    }

    public byte[] getSerial() {
        byte[] tTemp = new byte[4];
        if (Arrays.equals(tTemp, mSerial)) {
            doSelect();
        }
        return mSerial;
    }

    public String getNX() {
        String tReturn = null;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) 0x10;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == 0) {
                byte tBlock = (byte) 0x01;
                byte[] tRead = new byte[16];
                short tReadResult = mACR.read(mConnHandler, tBlock, tRead);
                if (tReadResult == (short) 0) {
                    StringBuilder tBuilder = new StringBuilder();
                    for (int i = 2; i < 16; i++) {
                        tBuilder.append(tRead[i]);
                    }
                    tReturn = doDecrypt(tBuilder.toString(), "key");
                } else {
                    System.out.println("Read process failed");
                }
            } else {
                System.out.println("Login process failed");
            }
        } else {
            System.out.println("Select process failed");
        }
        return tReturn;
    }

    public int getIE() {
        int tReturn = -1;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) 0x10;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == 0) {
                byte tBlock = (byte) 0x01;
                byte[] tRead = new byte[16];
                short tReadResult = mACR.read(mConnHandler, tBlock, tRead);
                if (tReadResult == (short) 0) {
                    tReturn = tRead[0];
                } else {
                    System.out.println("Read process failed");
                }
            } else {
                System.out.println("Login process failed");
            }
        } else {
            System.out.println("Select process failed");
        }
        return tReturn;
    }

    public int getMP() {
        int tReturn = -1;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) 0x10;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == 0) {
                byte tBlock = (byte) 0x01;
                byte[] tRead = new byte[16];
                short tReadResult = mACR.read(mConnHandler, tBlock, tRead);
                if (tReadResult == (short) 0) {
                    tReturn = tRead[1];
                } else {
                    System.out.println("Read process failed");
                }
            } else {
                System.out.println("Login process failed");
            }
        } else {
            System.out.println("Select process failed");
        }
        return tReturn;
    }
}
