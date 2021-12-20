package utils;

public class Auth {
    public static boolean passwordAuth(String username, String password) {
        Database db = new Database();
        String pass = db.getPassword(username);
        if (pass.equals(password)) {
            return true;
        }
        return false;
    }
}
