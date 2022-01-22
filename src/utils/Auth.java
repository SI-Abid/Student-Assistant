package utils;

import java.util.Date;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import io.github.cdimascio.dotenv.Dotenv;

public class Auth {

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<Document> users;

    public static void init() { 
        Dotenv env = Dotenv.load();
        String connString = env.get("CONNECTION_STRING");
        String db = env.get("DATABASE");
        client = MongoClients.create(connString);
        database = client.getDatabase(db);
        users = database.getCollection("user_info");
    }

    public static boolean passwordAuth(String username, String password) {
        Document doc = users.find(Filters.eq("username", username)).first();
        
        if (doc.get("password").equals(password)) {
            return true;
        }
        return false;
    }

    public static String[] getUserInfo(String username) {
        
        Document doc = users.find(Filters.eq("username", username)).first();
        String[] data = new String[3];
        data[0] = doc.getString("username");
        data[1] = doc.getString("fullname");
        data[2] = doc.getString("email");
        return data;
    }

    public static boolean isRegistered(String email, String username) {
        
        Document doc = users.find(Filters.eq("email", email)).first();
        if (doc != null) {
            return true;
        }
        doc = users.find(Filters.eq("username", username)).first();
        if (doc != null) {
            return true;
        }
        return false;
    }

    public static boolean addNewUser(String username, String password, String fullname, String email) {
        
        Document doc = new Document("username", username)
                .append("password", password)
                .append("fullname", fullname)
                .append("email", email);
        try {
            users.insertOne(doc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void updateLogin(String username) {
        users = database.getCollection("login_history");
        Document doc = users.find(Filters.eq("username", username)).first();
        if (doc == null) {
            doc = new Document("username", username)
                    .append("last_login", new Date().toString());
            users.insertOne(doc);
        } else {
            users.updateOne(Filters.eq("username", username), new Document("$set", new Document("last_login", new Date().toString())));
        }
    }

}
