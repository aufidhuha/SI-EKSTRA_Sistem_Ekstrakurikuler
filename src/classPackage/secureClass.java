/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classPackage;

import java.sql.*;
import javax.swing.JOptionPane;
import framePackage.loginFrame;

/**
 *
 * @author ASUS
 */
public class secureClass {

    private String username, password, lastUsername, newUsername, newPassword, pengguna;

    private Connection cnVAR;
    private PreparedStatement psVar;
    private Statement stVar;
    private ResultSet rsVar;
    private String query;

    public secureClass() {
        connectionClass koneksi = new connectionClass();
        cnVAR = koneksi.getConnection();
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPenguna(String pengguna) {
        this.pengguna = pengguna;
    }

    public String getLastUsername() {
        return lastUsername;
    }

    public void setLastUsername(String lastUsername) {
        this.lastUsername = lastUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean loginApp() {
        try {
            query = "SELECT * FROM user WHERE username = ? AND password = SHA2(?, 384)";

            psVar = cnVAR.prepareStatement(query);
            psVar.setString(1, this.username);
            psVar.setString(2, this.password);

            rsVar = psVar.executeQuery();
            return rsVar.next();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            return false;
        }
    }

    public int changeSecure() {

        int queryok = 0;

        try {
            query = "UPDATE user SET username = ?, password = SHA2(?, 384), pengguna = ? WHERE username = ?";

            psVar = cnVAR.prepareStatement(query);
            psVar.setString(1, this.newUsername);
            psVar.setString(2, this.newPassword);
            psVar.setString(3, this.pengguna);
            psVar.setString(4, this.lastUsername);

            int yesOrNo = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin mengubah \nUsername dan Password yang baru?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (yesOrNo == JOptionPane.YES_OPTION) {
                queryok = psVar.executeUpdate();
                return queryok;
            } else {
                return -1;
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
            return 0;
        }
        
    }

}
