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
public class ekstraClass {

    private String kodeEkstra, namaEkstra, nipPembina;

    private final Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public ekstraClass() {
        connectionClass connection = new connectionClass();
        this.cnVar = connection.getConnection();
    }

    public String getkodeEkstra() {
        return this.kodeEkstra;
    }

    public void setKodeEkstra(String kodeEkstra) {
        this.kodeEkstra = kodeEkstra;
    }

    public String getNamaEkstra() {
        return this.namaEkstra;
    }

    public void setNamaEkstra(String namaEkstra) {
        this.namaEkstra = namaEkstra;
    }

    public String getNipPembina() {
        return nipPembina;
    }

    public void setNipPembina(String nipPembina) {
        this.nipPembina = nipPembina;
    }

    public String autoIDExtra() {

        query = "SELECT MAX(id_ekstra) AS maxID FROM ekstra";
        String autoID = "";

        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            if (rsVar.next()) {
                String maxID = rsVar.getString("maxID");
                if (maxID == null) {
                    autoID = "KDE001"; //ID pertama
                } else {
                    int id = Integer.parseInt(maxID.substring(maxID.length() - 3)) + 1;
                    autoID = "KDE" + String.format("%03d", id);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());;
        }

        return autoID;
    }

    /* public String getNamaPembina(String namaPembina) {

        try {
            query = "SELECT nip FROM pembina WHERE nama = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, namaPembina);
            rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                return this.nipPembina = rsVar.getString("nip");
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            return "";
        }
        return "";
    } */

    public void saveDataEsktra() {

        try {
            query = "SELECT * FROM ekstra WHERE id_ekstra = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeEkstra);
            rsVar = psVar.executeQuery();

            if (!rsVar.next()) {

                query = "INSERT INTO ekstra VALUES (?, ?, ?)";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.kodeEkstra);
                psVar.setString(2, this.namaEkstra);
                psVar.setString(3, this.nipPembina);
                psVar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

            } else {

                query = "UPDATE ekstra SET nama_ekstra = ?, nip_pembina = ? WHERE id_ekstra = ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.namaEkstra);
                psVar.setString(2, this.nipPembina);
                psVar.setString(3, this.kodeEkstra);
                psVar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public void daleteDataEkstra() {

        try {
            query = "DELETE FROM ekstra WHERE id_ekstra = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeEkstra);
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {

                psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }

    }

    public ResultSet showDataEkstra() {

        query = "SELECT ekstra.id_ekstra, ekstra.nama_ekstra, pembina.nama, pembina.nip FROM ekstra LEFT JOIN pembina ON ekstra.nip_pembina = pembina.nip ORDER BY ekstra.id_ekstra ASC";

        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "EROR : " + sQLException.getMessage());
        }

        return rsVar;
    }
    
    
    public ResultSet showStatiska() {
        query = " SELECT ekstra.nama_ekstra, COUNT(DISTINCT ekstrasiswa.nisn) AS jumlah_siswa FROM ekstra LEFT JOIN ekstrasiswa ON ekstra.id_ekstra = ekstrasiswa.id_ekstra AND ekstrasiswa.status = 'Aktif' GROUP BY ekstra.nama_ekstra ORDER BY jumlah_siswa DESC";
        try {

            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

}
