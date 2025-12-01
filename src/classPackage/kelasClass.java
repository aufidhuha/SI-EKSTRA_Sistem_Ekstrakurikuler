/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class kelasClass {

    private String kodeKelas, namaKelas;

    private final Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public kelasClass() {
        connectionClass connection = new connectionClass();
        this.cnVar = connection.getConnection();
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String autoIDKelas() {
        String autoID = "";
        query = "SELECT MAX(id_kelas) AS maxID FROM kelas";

        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            if (rsVar.next()) {
                String maxID = rsVar.getString("maxID");
                if (maxID == null) {
                    autoID = "KDK01"; // id pertama
                } else {
                    int id = Integer.parseInt(maxID.substring(maxID.length() - 2)) + 1;
                    autoID = "KDK" + String.format("%02d", id);
                }
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());;
        }
        return autoID;
    }

    public void saveDataKelas() {
        query = "SELECT * FROM kelas WHERE id_kelas = ?";

        try {
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeKelas);
            rsVar = psVar.executeQuery();

            if (!rsVar.next()) {

                query = "INSERT INTO kelas VALUES (?, ?)";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.kodeKelas);
                psVar.setString(2, this.namaKelas);
                psVar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

            } else {

                query = "UPDATE kelas SET nama_kelas = ? WHERE id_kelas = ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.namaKelas);
                psVar.setString(2, this.kodeKelas);
                psVar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        
    }

    public void deleteDataKelas() {

        try {
            query = "DELETE FROM kelas WHERE id_kelas = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeKelas);

            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {

                psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        
    }

    public ResultSet showKelas() {
        query = "SELECT * FROM kelas ORDER BY id_kelas ASC";

        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

}
