package utils;

import java.io.FileInputStream;

public class Secret {
    public static String getPassword() {
        try (FileInputStream f = new FileInputStream("SECRET.txt");) {
            byte[] b = new byte[f.available()];
            f.read(b);
            return new String(b);
        } catch (Exception e) {
            System.err.println(e);
            return "nopass";
        }
    }
}
