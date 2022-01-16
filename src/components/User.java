package components;

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

        return new ProfilePanel(Username, FullName, Email).getPanel();
    }

}
