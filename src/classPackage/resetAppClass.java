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
public class resetAppClass {
    
    private Connection cnVar;
   
    public resetAppClass() {
        connectionClass connection = new connectionClass();
        cnVar = connection.getConnection();
    }
    
    
    public void resetApp(){
        
        try {
            String query1 = "DELETE FROM ekstrasiswa";
            String query2 = "DELETE FROM ekstra";
            String query3 = "DELETE FROM siswa";
            String query4 = "DELETE FROM pembina";
            String query5 = "DELETE FROM kelas";
            String query6 = "DELETE FROM user";
            String query7 = "INSERT INTO user VALUES ('admin', SHA2('admin', 384), 'ADMIN')";
            
            PreparedStatement psVar1 = cnVar.prepareStatement(query1);
            PreparedStatement psVar2 = cnVar.prepareStatement(query2);
            PreparedStatement psVar3 = cnVar.prepareStatement(query3);
            PreparedStatement psVar4 = cnVar.prepareStatement(query4);
            PreparedStatement psVar5 = cnVar.prepareStatement(query5);
            PreparedStatement psVar6 = cnVar.prepareStatement(query6);
            PreparedStatement psVar7 = cnVar.prepareStatement(query7);
            
            psVar1.executeUpdate();
            psVar2.executeUpdate();
            psVar3.executeUpdate();
            psVar4.executeUpdate();
            psVar5.executeUpdate();
            psVar6.executeUpdate();
            psVar7.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Aplikasi berhasil di Reset");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
}
