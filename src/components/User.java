package components;

import utils.Auth;
import utils.Database;

public class User {

    String FullName;
    String Username;
    String Password;
    String Email;
    boolean isLoggedIn=false;
    
    public void login(String username, String password) {

        isLoggedIn=Auth.passwordAuth(username, password);
        
        if(isLoggedIn) {
            String[] data = new Database().getUserInfo(username);
            this.Username = username;
            this.Password = password;
            this.FullName = data[2];
            this.Email = data[3];
        }
    }

    public boolean register(String fullName, String username, String password, String email) {
        Database db = new Database();

        if (db.isRegistered(email)) {
            return false;
        }
        
        String[] data = new String[4];
        data[0] = username;
        data[1] = password;
        data[2] = fullName;
        data[3] = email;

        return db.insert("users", data);
    }

    public boolean isVerified() {
        return isLoggedIn;
    }
}