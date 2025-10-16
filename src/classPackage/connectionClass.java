/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classPackage;

import javax.swing.JOptionPane;
import java.sql.*;


/**
 *
 * @author ASUS
 */
public class connectionClass {
    private String localhost = "localhost";
    private String db = "coba_ekskul";
    private String user = "root";
    private String pass = "";
    private String port = "3306";
    private String url = "jdbc:mysql://" + localhost + ":" + port + "/" + db;
    private Connection cnVar;
    
    public Connection getConnection(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            cnVar = DriverManager.getConnection(url, user, pass);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return cnVar;
    }

}
