/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author asus-K40IJ
 */
public class Reader {
    
    private static Reader mSingleton = new Reader();
    
    public static Reader getSingleton() {
        if (mSingleton == null) {
            mSingleton = new Reader();
        }
        return mSingleton;
    }

    public Reader() {
        
    }
}
