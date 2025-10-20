/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classPackage;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class siswaClass {

    private String kode_ekstra, nisn, nama, jk, id_kelas, id_ekstra, status;

    private Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public siswaClass() {
        connectionClass connection = new connectionClass();
        cnVar = connection.getConnection();
    }

    public void setKodeEkstraSiswa(String Kode) {
        this.kode_ekstra = Kode;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public void setId_ekstra(String id_ekstra) {
        this.id_ekstra = id_ekstra;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultSet showDataSiswa(String keaktifan) {
        try {
            query = "SELECT ekstrasiswa.kode_ekstra,ekstrasiswa.nisn,ekstrasiswa.nama,ekstrasiswa.jenis,kelas.nama_kelas,ekstra.nama_ekstra,ekstrasiswa.status FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON kelas.id_kelas = ekstrasiswa.id_kelas WHERE ekstrasiswa.status = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, keaktifan);
            rsVar = psVar.executeQuery();
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

    public ResultSet showSimpleDataNonAktif(String keaktifan) {
        try {
            query = "SELECT ekstrasiswa.nisn,ekstrasiswa.nama,kelas.nama_kelas,ekstra.nama_ekstra FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON kelas.id_kelas = ekstrasiswa.id_kelas WHERE status = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, keaktifan);
            rsVar = psVar.executeQuery();
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rsVar;
    }

    public DefaultTableModel showDataSiswaAll() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode Ekstra");
        model.addColumn("NISN");
        model.addColumn("Nama Siswa");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Kelas");
        model.addColumn("Ekstrakurikuler");
        model.addColumn("Status");

        try {
            query = "SELECT ekstrasiswa.kode_ekstra,ekstrasiswa.nisn,ekstrasiswa.nama,ekstrasiswa.jenis,kelas.nama_kelas,ekstra.nama_ekstra,ekstrasiswa.status FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON kelas.id_kelas = ekstrasiswa.id_kelas WHERE ekstrasiswa.status = 'Aktif'";

            psVar = cnVar.prepareStatement(query);

            rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                String id = rsVar.getString("kode_ekstra");
                String nisn = rsVar.getString("nisn");
                String nama = rsVar.getString("nama");
                String jenis = rsVar.getString("jenis");
                String kelass = rsVar.getString("nama_kelas");
                String ekstra = rsVar.getString("nama_ekstra");
                String status = rsVar.getString("status");

                Object[] data = {id, nisn, nama, jenis, kelass, ekstra, status};
                model.addRow(data);
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return model;
    }

    public String getNamaKelas(String namaKelas) {
        try {
            query = "SELECT * FROM kelas WHERE nama_kelas = ?";
            PreparedStatement psVar = cnVar.prepareStatement(query);
            psVar.setString(1, namaKelas);
            ResultSet rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                return this.id_kelas = rsVar.getString("id_kelas");
            }

        } catch (SQLException sQLException) {
            return "";
        }

        return "";
    }

    public String getNamaEkstra(String namaEkstra) {
        try {
            query = "SELECT * FROM ekstra WHERE nama_ekstra = ?";
            PreparedStatement psVar = cnVar.prepareStatement(query);
            psVar.setString(1, namaEkstra);
            ResultSet rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                return this.id_ekstra = rsVar.getString("id_ekstra");
            }

        } catch (SQLException sQLException) {
            return "";
        }

        return "";
    }

    public void saveDataSiswa() {
        try {
            query = "SELECT * FROM ekstrasiswa WHERE kode_ekstra = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kode_ekstra);
            rsVar = psVar.executeQuery();

            if (!rsVar.next()) {

                query = "SELECT * FROM siswa WHERE nisn = ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.nisn);
                rsVar = psVar.executeQuery();

                if (rsVar.next()) {

                    query = "INSERT INTO ekstrasiswa VALUES (?,?,?,?,?,?,?)";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.kode_ekstra);
                    psVar.setString(2, this.nisn);
                    psVar.setString(3, this.nama);
                    psVar.setString(4, this.jk);
                    psVar.setString(5, this.id_kelas);
                    psVar.setString(6, this.id_ekstra);
                    psVar.setString(7, this.status);
                    psVar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

                } else {

                    query = "INSERT INTO siswa VALUES (?,?)";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.nisn);
                    psVar.setString(2, this.nama);
                    psVar.executeUpdate();

                    query = "INSERT INTO ekstrasiswa VALUES (?,?,?,?,?,?,?)";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.kode_ekstra);
                    psVar.setString(2, this.nisn);
                    psVar.setString(3, this.nama);
                    psVar.setString(4, this.jk);
                    psVar.setString(5, this.id_kelas);
                    psVar.setString(6, this.id_ekstra);
                    psVar.setString(7, this.status);
                    psVar.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                }

            } else {

                query = "SELECT * FROM siswa WHERE nisn = ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.nisn);
                rsVar = psVar.executeQuery();

                if (rsVar.next()) {

                    query = "UPDATE ekstrasiswa SET nisn = ?, nama = ?, jenis = ?, id_kelas = ?, id_ekstra = ?, status = ? WHERE kode_ekstra = ?";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.nisn);
                    psVar.setString(2, this.nama);
                    psVar.setString(3, this.jk);
                    psVar.setString(4, this.id_kelas);
                    psVar.setString(5, this.id_ekstra);
                    psVar.setString(6, this.status);
                    psVar.setString(7, this.kode_ekstra);
                    psVar.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");

                } else {

                    query = "INSERT INTO siswa VALUES (?, ?)";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.nisn);
                    psVar.setString(2, this.nama);
                    psVar.executeUpdate();
                    psVar.close();

                    query = "UPDATE ekstrasiswa SET nisn = ?, nama = ?, jenis = ?, id_kelas = ?, id_ekstra = ?, status = ? WHERE kode_ekstra = ?";
                    psVar = cnVar.prepareStatement(query);
                    psVar.setString(1, this.nisn);
                    psVar.setString(2, this.nama);
                    psVar.setString(3, this.jk);
                    psVar.setString(4, this.id_kelas);
                    psVar.setString(5, this.id_ekstra);
                    psVar.setString(6, this.status);
                    psVar.setString(7, this.kode_ekstra);
                    psVar.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");

                }
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());

        }
    }

    public void deleteDataSiswa() {

        try {

            query = "DELETE FROM ekstrasiswa WHERE kode_ekstra = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kode_ekstra);

            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {

                psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error" + sQLException.getMessage());
        }
    }

    public void deleteDataSiswaAllStatus(String status) {

        try {

            query = "DELETE FROM ekstrasiswa WHERE status = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, status);

            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus semua data ini?\n\nNote : Data akan dihapus secara keseluruhan, kehilangan data tidak bisa dihindarkan", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {

                int rowDelete = psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Siswa " + status + " berhasil dihapus\n\nNote : " + rowDelete + " Data Siswa " + status + "dihapus");

            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }

    public String autoIDDataEkstra() {
        String autoID = "";
        query = "SELECT MAX(kode_ekstra) AS maxID FROM ekstrasiswa";
        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            if (rsVar.next()) {
                String maxID = rsVar.getString("maxID");
                if (maxID == null) {
                    autoID = "KDEK0001"; //ID pertama
                } else {
                    int id = Integer.parseInt(maxID.substring(4)) + 1;
                    autoID = "KDEK" + String.format("%04d", id);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return autoID;
    }

    public DefaultTableModel cariDataEkstra(String namaEkstra) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode Ekstra");
        model.addColumn("NISN");
        model.addColumn("Nama Siswa");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Kelas");
        model.addColumn("Ekstrakurikuler");
        model.addColumn("Status");

        try {
            query = "SELECT ekstrasiswa.kode_ekstra,ekstrasiswa.nisn,ekstrasiswa.nama,ekstrasiswa.jenis,kelas.nama_kelas,ekstra.nama_ekstra,ekstrasiswa.status FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON kelas.id_kelas = ekstrasiswa.id_kelas WHERE ekstrasiswa.status = 'Aktif' AND ekstra.nama_ekstra = ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, namaEkstra);
            rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                String id = rsVar.getString("kode_ekstra");
                String nisn = rsVar.getString("nisn");
                String nama = rsVar.getString("nama");
                String jenis = rsVar.getString("jenis");
                String kelass = rsVar.getString("nama_kelas");
                String ekstra = rsVar.getString("nama_ekstra");
                String status = rsVar.getString("status");

                Object[] data = {id, nisn, nama, jenis, kelass, ekstra, status};
                model.addRow(data);
            }

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());

        }
        return model;
    }

    public DefaultTableModel cariDataNamaSiswa(String statusSiswa, String namaSiswa) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode Ekstra");
        model.addColumn("NISN");
        model.addColumn("Nama Siswa");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Kelas");
        model.addColumn("Ekstrakurikuler");
        model.addColumn("Status");

        try {
            query = "SELECT ekstrasiswa.kode_ekstra,ekstrasiswa.nisn,ekstrasiswa.nama,ekstrasiswa.jenis,kelas.nama_kelas,ekstra.nama_ekstra,ekstrasiswa.status FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON kelas.id_kelas = ekstrasiswa.id_kelas WHERE ekstrasiswa.status = ? AND ekstrasiswa.nama LIKE ?";

            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, statusSiswa);
            psVar.setString(2, "%" + namaSiswa + "%");
            rsVar = psVar.executeQuery();

            while (rsVar.next()) {
                String id = rsVar.getString("kode_ekstra");
                String nisn = rsVar.getString("nisn");
                String nama = rsVar.getString("nama");
                String jenis = rsVar.getString("jenis");
                String kelass = rsVar.getString("nama_kelas");
                String ekstra = rsVar.getString("nama_ekstra");
                String status = rsVar.getString("status");

                Object[] data = {id, nisn, nama, jenis, kelass, ekstra, status};
                model.addRow(data);
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());

        }
        return model;
    }

    public DefaultTableModel showDataAbsensiAll() {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nama Siswa");
        model.addColumn("Kelas");
        model.addColumn("Ekstrakurikuler");
        model.addColumn("Keterangan");

        try {
            query = "SELECT ekstrasiswa.nama, kelas.nama_kelas, ekstra.nama_ekstra FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON ekstrasiswa.id_kelas = kelas.id_kelas WHERE ekstrasiswa.status = 'Aktif'";
            
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            while (rsVar.next()) {                
                String namaSiswa = rsVar.getString("nama");
                String namaKelas = rsVar.getString("nama_kelas");
                String namaEkstra = rsVar.getString("nama_ekstra");
                
                Object data[] = {namaSiswa, namaKelas, namaEkstra};
                model.addRow(data);
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return model;
    }
    
    public DefaultTableModel showDataAbsensiKategori(String cariNamaEkstra) {

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nama Siswa");
        model.addColumn("Kelas");
        model.addColumn("Ekstrakurikuler");
        model.addColumn("Keterangan");

        try {
            query = "SELECT ekstrasiswa.nama, kelas.nama_kelas, ekstra.nama_ekstra FROM ekstrasiswa LEFT JOIN ekstra ON ekstrasiswa.id_ekstra = ekstra.id_ekstra LEFT JOIN kelas ON ekstrasiswa.id_kelas = kelas.id_kelas WHERE ekstrasiswa.status = 'Aktif' AND ekstra.nama_ekstra LIKE ?";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, "%" + cariNamaEkstra + "%");
            rsVar = psVar.executeQuery();
            
            while (rsVar.next()) {                
                String namaSiswa = rsVar.getString("nama");
                String namaKelas = rsVar.getString("nama_kelas");
                String namaEkstra = rsVar.getString("nama_ekstra");
                
                Object data[] = {namaSiswa, namaKelas, namaEkstra};
                model.addRow(data);
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return model;
    }
}
