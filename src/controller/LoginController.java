package controller;

import dao.UsuarioDAO;
import view.LoginView;
import view.FuncionarioView;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView view;
    private UsuarioDAO dao;
    
    public LoginController(LoginView view) {
        this.view = view;
        this.dao = new UsuarioDAO();
        
    }
    
    public boolean fazerLogin(String email, String senha) {
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos");
            return false;
        }
        
        boolean sucesso = dao.validarLogin(email, senha);
        if (sucesso) {
            FuncionarioView funcionarioView = new FuncionarioView();
            FuncionarioController funcionarioController = new FuncionarioController(funcionarioView);
            funcionarioController.carregarFuncionarios();
        
            funcionarioView.setVisible(true);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Email ou senha inválidos");
        }
        
        return sucesso;
    }
}