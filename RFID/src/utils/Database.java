/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus-K40IJ
 */
public class Database {

    private static Database mSingleton = new Database();
    private Statement mStatement;
    private Connection mConnection;
    private String mDBName = "jdbc:mysql://isc.comlabs.itb.ac.id/absenpti";
    private String mDBUser = "root";
    private String mDBPass = "";

    public static Database getSingleton() {
        if (mSingleton == null) {
            mSingleton = new Database();
        }
        return mSingleton;
    }

    private Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mConnection = DriverManager.getConnection(mDBName, mDBUser, mDBPass);
            mStatement = mConnection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return mConnection;
    }

    public Statement getStatement() {
        return mStatement;
    }

    public ResultSet getQuery(String pQuery) {
        ResultSet tReturn = null;
        try {
            tReturn = mStatement.executeQuery(pQuery);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tReturn;
    }

    public long doQuery(String pQuery) {
        long tReturn = -1;
        try {
            mStatement.executeUpdate(pQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet tTemp = mStatement.getGeneratedKeys();
            //tReturn = tTemp.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tReturn;
    }

    public int getRowCount(ResultSet pSet) {
        int tReturn = 0;
        try {
            pSet.last();
            tReturn = pSet.getRow();
            pSet.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tReturn;
    }
}
