
import Utils.Reader;
import Utils.Database;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    private boolean isSama = false;
    private String currDate = "";
    private String sqlDate = "";
    private GregorianCalendar cal;

    public MainThread(Report report) {
        this.report = report;
        cal = new GregorianCalendar();
        long tahun = cal.get(Calendar.YEAR);
        long bulan = (cal.get(Calendar.MONTH) + 1);
        long tanggal = cal.get(Calendar.DATE);
        currDate = tanggal + "/" + bulan + "/" + tahun;
        sqlDate = tahun + "-" + bulan + "-" + tanggal;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Reader.getSingleton().getNX(16, 1) != null) {
                    if (!isSama) {
                        String nim = Reader.getSingleton().getNX(16, 1);
                        cal = new GregorianCalendar();
                        long jam = cal.get(Calendar.HOUR_OF_DAY);
                        long menit = cal.get(Calendar.MINUTE);
                        long detik = cal.get(Calendar.SECOND);
                        String time = jam + ":" + menit + ":" + detik;
                        cek_database(nim, time, "RFID");
                        isSama = true;
                    }
                } else {
                    isSama = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public void cek_database(String nim, String time, String ket) throws SQLException {

        String pQuery = "SELECT * FROM asisten_pti_praktikan WHERE nim = " + nim;
        ResultSet hasil = result.getQuery(pQuery);
        if (hasil.next()) {
            int idkelas = hasil.getInt("id_kelas");
            pQuery = "SELECT * FROM asisten_pti_pertemuan WHERE id_kelas = " + idkelas + " AND absen_buka < '" + time + "' AND absen_tutup > '" + time + "' AND tanggal = '" + sqlDate + "'";
            hasil = result.getQuery(pQuery);
            if (hasil.next()) {
                int pertemuan = hasil.getInt("id_pertemuan");
                pQuery = "SELECT * FROM asisten_pti_absen_praktikan WHERE nim = " + nim + " AND id_pertemuan = '" + pertemuan + "'";
                hasil = result.getQuery(pQuery);
                if (!(hasil.next())) {
                    pQuery = "INSERT INTO asisten_pti_absen_praktikan (nim,id_pertemuan,keterangan) VALUES (" + nim + "," + pertemuan + ",'" + ket + "')";
                    result.doQuery(pQuery);
                    String query = "SELECT * FROM asisten_pti_kelas WHERE id_kelas = " + idkelas;
                    ResultSet res = result.getQuery(query);
                    res.next();
                    report.getNIM().setText(nim);
                    report.getDate().setText(currDate);
                    report.getTime().setText(time);
                    report.getKelas().setText("" + res.getString("shift"));
                    report.berhasilAbsen();
                    File tFile = new File("./others/beep-6.wav");
                    try {
                        AudioInputStream tAudio = AudioSystem.getAudioInputStream(tFile);
                        Clip tClip = AudioSystem.getClip();
                        tClip.open(tAudio);
                        tClip.start();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    report.gagalAbsen();
                    File tFile = new File("./others/beep-5.wav");
                    try {
                        AudioInputStream tAudio = AudioSystem.getAudioInputStream(tFile);
                        Clip tClip = AudioSystem.getClip();
                        tClip.open(tAudio);
                        tClip.start();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                report.kelasSalah();
            }
        } else {
            report.nimSalah();
        }

    }
}
