package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.DatabaseConnection;
import util.PasswordUtil;

public class UsuarioDAO {
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, PasswordUtil.hashPassword(senha));
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedHash = rs.getString("senha");
                String inputHash = PasswordUtil.hashPassword(senha);
                return storedHash.equals(inputHash);
            }
            
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}