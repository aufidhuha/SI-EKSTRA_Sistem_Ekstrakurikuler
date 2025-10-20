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
public class pembinaClass {
    
    private String nip, nama;
    
    private Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public pembinaClass() {
        connectionClass connection = new connectionClass();
        cnVar = connection.getConnection();
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    public void saveData(){
        
        try {
            query = "SELECT * FROM pembina WHERE nip = ?";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.nip);
            rsVar = psVar.executeQuery();
            
            if (!rsVar.next()) {
                
                query = "INSERT INTO pembina VALUES (?, ?)";
                
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.nip);
                psVar.setString(2, this.nama);
                psVar.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                
            } else {
                
                query = "UPDATE pembina SET nama = ? WHERE nip = ?";
                
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.nama);
                psVar.setString(2, this.nip);
                psVar.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
    
    public void deleteData(){
        
        try {
            query = "DELETE FROM pembina WHERE nip = ?";
            
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.nip);
            
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (yesOrNo == JOptionPane.YES_OPTION) {
                psVar.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
    
    public DefaultTableModel showDataPembina(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("NIP");
        model.addColumn("Nama Pembina");
        
        try {
            query = "SELECT * FROM pembina ORDER BY nip ASC";
            
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            while (rsVar.next()) {                
                
                String nip = rsVar.getString("nip");
                String nama = rsVar.getString("nama");
                
                Object[] data = {nip, nama};
                model.addRow(data);
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return model;
    }
    
    
    public DefaultTableModel showNamaPembina(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Nama Guru atau Pembina");
        
        try {
            query = "SELECT nama FROM pembina ORDER BY nip ASC";
            
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            
            while (rsVar.next()) { 
                
                String nama = rsVar.getString("nama");
                
                Object data[] = {nama};
                model.addRow(data);
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        
        return model;
    }
    
}
