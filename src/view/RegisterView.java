package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.RegisterController;

public class RegisterView extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private RegisterController controller;

    
    public RegisterView() {
        setTitle("Cadastro");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        this.controller = new RegisterController(this);
        
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);
        
        panel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        JButton registerButton = new JButton("Cadastrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nameField.getText();
                String email = emailField.getText();
                String senha = new String(passwordField.getPassword());
                
                controller.cadastrarUsuario(nome, email, senha);
            }
        });
        
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView().setVisible(true);
                dispose();
            }
        });
        
        panel.add(registerButton);
        panel.add(backButton);
        
        add(panel);
    }
}