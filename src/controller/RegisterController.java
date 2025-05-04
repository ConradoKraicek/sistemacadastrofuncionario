package controller;

import dao.UsuarioDAO;
import view.RegisterView;
import javax.swing.JOptionPane;
import view.LoginView;

public class RegisterController {

    private RegisterView view;
    private UsuarioDAO usuarioDAO;

    public RegisterController(RegisterView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
    }

    public void cadastrarUsuario(String nome, String email, String senha) {
        
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            showWarning("Preencha todos os campos!");
            return;
        }

        if (nome.length() < 3) {
            showWarning("Nome deve ter pelo menos 3 caracteres!");
            return;
        }

        if (!validarEmail(email)) {
            showWarning("Email inválido! Use o formato: usuario@dominio.com");
            return;
        }

        if (senha.length() < 6) {
            showWarning("Senha deve ter no mínimo 6 caracteres!");
            return;
        }

        try {
            if (usuarioDAO.cadastrarUsuario(nome, email, senha)) {
                JOptionPane.showMessageDialog(view,
                        "Cadastro realizado com sucesso!\nFaça login para continuar.",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                new LoginView().setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao cadastrar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(view, message, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    private boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
