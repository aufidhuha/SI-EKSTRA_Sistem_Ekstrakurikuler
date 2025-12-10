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
public class profilClass {

    private String nama_lembaga, alamat, kodePos, telepon, email;

    private final Connection cnVar;
    private PreparedStatement psVar, psVar1, psVar2;
    private Statement stVar;
    private ResultSet rsVar;
    private String query, query1, query2;

    public profilClass() {
        connectionClass connection = new connectionClass();
        this.cnVar = connection.getConnection();
    }

    public String getNama_lembaga() {
        return nama_lembaga;
    }

    public void setNama_lembaga(String nama_lembaga) {
        this.nama_lembaga = nama_lembaga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void saveData() {

        try {
            query = "SELECT * FROM profil";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);

            if (!rsVar.next()) {

                query = "INSERT INTO profil VALUES (?, ?, ?, ?, ?)";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.nama_lembaga);
                psVar.setString(2, this.alamat);
                psVar.setString(3, this.kodePos);
                psVar.setString(4, this.telepon);
                psVar.setString(5, this.email);
                psVar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data profil lembaga berhasil disimpan");

            } else {

                query1 = "DELETE FROM profil";
                query2 = "INSERT INTO profil VALUES (?, ?, ?, ?, ?)";

                psVar1 = cnVar.prepareStatement(query1);
                psVar2 = cnVar.prepareStatement(query2);

                psVar2.setString(1, this.nama_lembaga);
                psVar2.setString(2, this.alamat);
                psVar2.setString(3, this.kodePos);
                psVar2.setString(4, this.telepon);
                psVar2.setString(5, this.email);

                psVar1.executeUpdate();
                psVar2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data profil lembaga berhasil disimpan");
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void deleteData() {

        try {
            query = "DELETE FROM profil";
            psVar = cnVar.prepareStatement(query);

            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus Profil Lembaga?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {
                psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data profil lembaga berhasil dihapus");
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public ResultSet showData() {
        try {
            query = "SELECT * FROM profil";
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

}
