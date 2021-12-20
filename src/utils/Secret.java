package utils;

import java.io.FileInputStream;

public class Secret {
    
    public static String getLoginCreds() {
        try {
            FileInputStream fis = new FileInputStream(".csv");
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            return new String(data);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public static String getDB_URL() {
        return getLoginCreds() == null ? null : getLoginCreds().split(",")[0];
    }

    public static String getDB_USER() {
        return getLoginCreds() == null ? null : getLoginCreds().split(",")[1];
    }

    public static String getDB_PASS() {
        return getLoginCreds() == null ? null : getLoginCreds().split(",")[2];
    }
}
