package components;

import utils.Auth;

public class User {

    String FullName;
    String Username;
    String Password;
    String Email;
    boolean isLoggedIn=false;
    
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
        // Database db = new Database();

        if (Auth.isRegistered(email, username)) {
            return false;
        }

        return Auth.addNewUser(username, password, fullName, email);
    }

    public boolean isVerified() {
        return isLoggedIn;
    }
}
