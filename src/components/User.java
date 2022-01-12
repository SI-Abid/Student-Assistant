package components;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Auth;

public class User {

    String FullName;
    String Username;
    String Password;
    String Email;
    boolean isLoggedIn=false;
    JPanel userPanel;
    
    public void login(String username, String password) {

        isLoggedIn=Auth.passwordAuth(username, password);
        
        if(isLoggedIn) {
            String[] data = Auth.getUserInfo(username);
            this.Username = username;
            this.Password = password;
            this.FullName = data[2];
            this.Email = data[3];
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
        panel.setSize(100, 50);
        panel.setBounds(0, 0, 200, 200);

        JLabel label = new JLabel("Welcome " + this.FullName);
        label.setBounds(10, 10, 200, 20);
        panel.add(label);

        return panel;
    }

}
