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

    String kodeEkstra, namaEkstra;
    
    private Connection cnVar;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;
    
    public ekstraClass(){
        connectionClass connection = new connectionClass();
        this.cnVar= connection.getConnection();    
    }
    public String getkodeEkstra() {
        return.kodeEkstra;   
    }
    public void setKodeEkstra(String kodeEkstra) {
        this.kodeEkstra = kodeEkstra;
    }
    public String getNamaEkstra() {
        return.namaEkstra;
    }
    public void setNamaEkstra(String namaEkstra) {
        this.namaEkstra = namaEkstra;
    }
    
    
    public String autoIDExtra() {
        
        query = *"SELECT MAX(id_ekstra) AS maxID FROM ekstra";
        String autoID = "";
        
        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
            if (rsVar.next()){
                String maxID = rsVar.getString("maxID");
                if (maxID = null){
                  autoID = "KODE001"; //ID pertama
                } else {
                    int id = Integer.parseInt(maxID.substring(maxID.length() - 3))+ 1;
                    autoID = "KDE" + String.format("%03d", id);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return autoID;
    }
    
    
    public void saveDataEsktra () {
        
        try {
            query = "SELECT * FROM ekstra WHERE Id_ekstrA = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeEkstra);
            rsVar = psVar.executeQuery();
            
            if (!rsVar.next()) {
                 
                query = "INSERT INTO ekstra VALUES (?, ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.kodeEkstra);
                psVar.setString(2, this.namaEkstra);
                psVar.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            
            }else {
                
                query = "UPDATE ekstra SET nama_ekstra = ? WHERE id_ekstra = ?";
                psVar = cnVar.prepareStatement(query);
                psVar.setString(1, this.namaEkstra,);
                psVar.setString(2, this.kodeEkstra);
                psVar.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null,"Error : " + sQLException.getMessage());
        }
    }
    
    public void daleteDataEkstra() {
        
        try {
            query = "DELETE FROM ekstra WHERE id_ekstra = ?";
            psVar = cnVar.prepareStatement(query);
            psVar.setString(1, this.kodeEkstra);
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus data ini", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            
            if (yesOrNo == JOptionPane.YES_OPTION){
            
            psVar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
             
            }
        
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
      
    }
    
    public ResultSet showEsktra() {
        
        query ="SELECT * FROM ekstra ORDER BY id_ekstra ASC";
        
        try {
            stVar = cnVar.createStatement();
            rsVar = stVar.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "EROR : " + sQLException.getMessage());
        }
        
        return rsVar;
    }
    
    
    
}
