package components;

import java.awt.Color;

import javax.swing.*;

import utils.Auth;

public class User {

    String FullName;
    String Username;
    String Email;
    boolean isLoggedIn=false;
    JPanel userPanel;
    
    public void login(String username, String password) {

        isLoggedIn=Auth.passwordAuth(username, password);
        
        if(isLoggedIn) {
            String[] data = Auth.getUserInfo(username);
            this.Username = data[0];
            this.FullName = data[1];
            this.Email = data[2];
        }
    }

    public boolean register(String fullName, String username, String password, String email) {

        if (Auth.isRegistered(email, username)) {
            return false;
        }

        return Auth.addNewUser(username, password, fullName, email);
    }

    public boolean isVerified() {
        return isLoggedIn;
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(200, 100);
        panel.setBounds(0, 0, 200, 200);

        JLabel label = new JLabel("Welcome " + this.FullName);
        label.setForeground(Color.WHITE);
        label.setBounds(0, 15, 200, 20);
        panel.add(label);

        JLabel label2 = new JLabel("Username: " + this.Username);
        label2.setForeground(Color.WHITE);
        label2.setBounds(0, 40, 200, 20);
        panel.add(label2);

        return panel;
    }

}
