package main;

import javax.swing.UIManager;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}