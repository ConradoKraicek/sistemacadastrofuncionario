package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.LoginController;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    
    public LoginView() {
        LoginController controller = new LoginController(this);

        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);
        
        panel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String email = emailField.getText();
              String senha = new String(passwordField.getPassword());
              controller.fazerLogin(email, senha);
            }
        });
        
        JButton registerButton = new JButton("Cadastrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterView().setVisible(true);
                dispose();
            }
        });
        
        panel.add(loginButton);
        panel.add(registerButton);
        
        add(panel);
    }
}