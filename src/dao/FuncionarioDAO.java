package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionario;
import util.DatabaseConnection;

public class FuncionarioDAO {
    public boolean adicionarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (nome, data_admissao, salario, status) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isStatus());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
            
            return false;
        }
    }
    
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setDataAdmissao(rs.getDate("data_admissao"));
                f.setSalario(rs.getDouble("salario"));
                f.setStatus(rs.getBoolean("status"));
                
                funcionarios.add(f);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return funcionarios;
    }
}