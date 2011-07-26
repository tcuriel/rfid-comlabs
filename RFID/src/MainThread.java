
import Utils.Reader;
import Utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hyouda
 */
public class MainThread extends Thread {

    public Database result = Database.getSingleton();
    private Report report;

    public MainThread(Report report) {
        this.report = report;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Reader.getSingleton().getNX() != null) {
                    String nim = Reader.getSingleton().getNX();
//                    System.out.println("NIM : " + Reader.getSingleton().getNX());
                    GregorianCalendar cal = new GregorianCalendar();
                    long jam = cal.get(Calendar.HOUR_OF_DAY);
                    long menit = cal.get(Calendar.MINUTE);
                    long detik = cal.get(Calendar.SECOND);
                    long tahun = cal.get(Calendar.YEAR);
                    long bulan = (cal.get(Calendar.MONTH) + 1);
                    long tanggal = cal.get(Calendar.DATE);
                    //2011-07-26 01:58:40
                    String date = tahun + "-" + bulan + "-" + tanggal + " " + jam + ":" + menit + ":" + detik;
                    System.out.println(date + "|" + nim);
                    cek_database(nim, date);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public void cek_database(String nim, String date) throws SQLException {

        String pQuery = "SELECT * FROM mahasiswa WHERE nim = " + nim;
        ResultSet hasil = result.getQuery(pQuery);
        long hasildo;
        if (hasil.next()) {
            int idkelas = hasil.getInt("idkelas");
            pQuery = "SELECT * FROM pertemuan WHERE idkelas = " + idkelas + " AND mulai < '" + date + "' AND akhir > '" + date + "'";
            hasil = result.getQuery(pQuery);
            
            if (hasil.next()) {
                int pertemuan = hasil.getInt("idkelas");
                pQuery = "SELECT * FROM absen WHERE idkelas = " + idkelas + " AND nim = " + nim + " AND pertemuan = '" + pertemuan + "'";
                hasil = result.getQuery(pQuery);
                String query = "SELECT * FROM kelas WHERE idkelas = " + idkelas;
                ResultSet res = result.getQuery(query);
                if (!(hasil.next())) {
                    pQuery = "INSERT INTO absen (nim,idkelas,pertemuan) VALUES (" + nim + "," + idkelas + "," + pertemuan + ")";
                    hasildo = result.doQuery(pQuery);
                    report.getNim().setText(nim);
                    report.getWaktu().setText(date);
                    report.getFakultas().setText(res.getString("fakultas"));
                    report.getShift().setText(res.getString("shift"));
                    //System.out.println("berhasil masuk");
                }
            }
        }

    }
}
