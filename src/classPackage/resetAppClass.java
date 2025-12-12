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
    
    private final Connection cnVar;
    private PreparedStatement psVar1, psVar2, psVar3, psVar4, psVar5, psVar6, psVar7, psVar8;
    private String query1, query2, query3, query4, query5, query6, query7, query8;
    
    public resetAppClass() {
        connectionClass connection = new connectionClass();
        cnVar = connection.getConnection();
    }
    
    
    public void resetApp(){
        
        try {
            query1  = "DELETE FROM ekstrasiswa";
            query2  = "DELETE FROM ekstra";
            query3  = "DELETE FROM siswa";
            query4  = "DELETE FROM pembina";
            query5  = "DELETE FROM kelas";
            query6  = "DELETE FROM profil";
            query7  = "DELETE FROM user";
            query8  = "INSERT INTO user VALUES ('admin', SHA2('admin', 384), 'ADMIN')";
            
            psVar1 = cnVar.prepareStatement(query1);
            psVar2  = cnVar.prepareStatement(query2);
            psVar3  = cnVar.prepareStatement(query3);
            psVar4  = cnVar.prepareStatement(query4);
            psVar5  = cnVar.prepareStatement(query5);
            psVar6  = cnVar.prepareStatement(query6);
            psVar7  = cnVar.prepareStatement(query7);
            psVar8  = cnVar.prepareStatement(query8);
            
            psVar1.executeUpdate();
            psVar2.executeUpdate();
            psVar3.executeUpdate();
            psVar4.executeUpdate();
            psVar5.executeUpdate();
            psVar6.executeUpdate();
            psVar7.executeUpdate();
            psVar8.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Aplikasi berhasil di Reset");
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
    }
    
}
