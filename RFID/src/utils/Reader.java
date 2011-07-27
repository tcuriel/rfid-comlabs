/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import acs.jni.ACR120U;
import java.io.File;
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
        (byte) 0x22, (byte) 0xF2,
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
            File tFile1 = new File("./ACR120UJNI.dll"); 
            File tFile2 = new File("./ACR120U.dll"); 
            System.load(tFile1.getCanonicalPath());
            System.load(tFile2.getCanonicalPath());
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
            tTemp[0] = Byte.valueOf("" + pKey.charAt(1),16);
            tTemp[1] = Byte.valueOf("" + pKey.charAt(3),16);
            tTemp[2] = Byte.valueOf("" + pKey.charAt(4),16);
            int j = 0;
            for (int i = 0; i < pChiperText.length(); i++) {
                tBuilder.append((char)(pChiperText.charAt(i) ^ tTemp[j]));
                if (j < 2) {
                    j++;
                } else {
                    j = 0;
                }
            }
            return tBuilder.toString();
        } else {
            return null;
        }
    }

    private String doConvertSerial() {
        StringBuilder tBuilder = new StringBuilder();
        for (int i = 0; i < mSerial.length; i++) {
            byte b = mSerial[i];
            if (b < 0) {
                tBuilder.append(Integer.toHexString(b).toUpperCase().substring(Integer.toHexString(b).length() - 2, Integer.toHexString(b).length()));
            } else if (b < 16) {
                tBuilder.append('0').append(Integer.toHexString(b).toUpperCase());
            } else {
                tBuilder.append(Integer.toHexString(b).toUpperCase());
            }
        }
        return tBuilder.toString();
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

    public String getNX(int pNXSector, int pBlockSector) {
        String tReturn = null;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) pNXSector;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == (short) 0) {
                byte tBlock = (byte) (pBlockSector + pNXSector*4);
                byte[] tRead = new byte[16];
                short tReadResult = mACR.read(mConnHandler, tBlock, tRead);
                if (tReadResult == (short) 0) {
                    StringBuilder tBuilder = new StringBuilder();
                    for (int i = 2; i < 16; i++) {
                        if (tRead[i] != 0) {
                            tBuilder.append((char) tRead[i]);
                        }
                    }
                    tReturn = doDecrypt(tBuilder.toString(), doConvertSerial());
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

    public int getIE(int pNXSector, int pBlockSector) {
        int tReturn = -1;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) pNXSector;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == (short) 0) {
                byte tBlock = (byte) (pBlockSector + pNXSector*4);
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

    public int getMP(int pNXSector, int pBlockSector) {
        int tReturn = -1;
        short tSelectResult = doSelect();
        if (tSelectResult == (short) 0) {
            byte tSector = (byte) pNXSector;
            byte tKeyType = (byte) ACR120U.AC_MIFARE_LOGIN_KEYTYPE_A;
            byte tStoreNo = (byte) 0x00;
            short tLoginResult = mACR.login(mConnHandler, tSector, tKeyType, tStoreNo, pKey);
            if (tLoginResult == 0) {
                byte tBlock = (byte) (pBlockSector + pNXSector*4);
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
