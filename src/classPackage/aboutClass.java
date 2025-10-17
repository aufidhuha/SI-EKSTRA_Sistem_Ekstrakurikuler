/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classPackage;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class aboutClass {
    private String kelas, ekstra, pengguna, siswa, tentang;
    
    private Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;
    
    public aboutClass(){
        connectionClass connection = new connectionClass();
        this.cnVar = connection.getConnection();
    }
    
    public String jumlahKelas(){
        try {
            query = "SELECT COUNT(*) AS jumlah FROM kelas";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            if (rsVar.next()){
                return  this.kelas = rsVar.getString("jumlah");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return "";
        
    }
    
    public String jumlahEkstra(){
        try {
            query = "SELECT COUNT(*) AS jumlah FROM ekstra";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            if (rsVar.next()){
                return  this.ekstra = rsVar.getString("jumlah");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return "";
        
    }
    public String jumlahSiswa(){
        try {
            query = "SELECT COUNT(*) AS jumlah_siswa FROM ekstrasiswa WHERE status = 'Aktif'";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            if (rsVar.next()){
                return  this.siswa = rsVar.getString("jumlah_siswa");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return "";
        
    }
    
    public String jumlahPengguna(){
        try {
            query = "SELECT pengguna FROM user";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            if (rsVar.next()){
                return  this.pengguna = rsVar.getString("pengguna");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return "";
        
    }
    
    
    public String namaPengguna(){
        try {
            query = "SELECT pengguna FROM user";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            if (rsVar.next()){
                return  this.pengguna = rsVar.getString("pengguna");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return "";
        
    }
    
    public String keteranganTentang(){
        return this.tentang = "<html>"
                + "<body>"
                + "<p><b>Aplikasi SI-EKSTRA</b> atau <b>Aplikasi Sistem Ekstrakurikuler</b> merupakan aplikasi yang dapat digunakan untuk mempermudah pendapatan dan management terhadap ekstrakurikuler di lingkungan pendidikan <br> dan siswa yang mengikuti ekstrakurikuler tersebut <br><br>Aplikasi <b>SI-EKSTRA</b> dibuat oleh Mahasiswa <b>INSTITUT TEKNOLOGI MOJOSARI</b>, antara lain : "
                + "<pre style = \"font-family : 'Segoe-UI';\">"
                + "Anna Cahya Ningrum\t:     (202457201013)\n"
                + "Aufi Dhuha Mutaafif\t:     (202457201015)\n"
                + "Aura Medina\t\t:     (202457201016)\n"
                + "Citra Yuda\t\t:     (202457201019)\n"
                + "Helmy Rahmat Tullah\t:     (202457201024)\n"
                + "Ismatul Hani'ah\t\t:     (202457201026)\n"
                + "Wahyu Masrun Najib\t:     (202457201068)\n"
                + "</pre>"
                + "</p>"
                + "<body>"
                + "<html>";
    }

}
