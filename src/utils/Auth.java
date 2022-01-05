package utils;

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

    public Auth() {
        Dotenv env = Dotenv.load();
        String connString = env.get("CONNECTION_STRING");
        String db = env.get("DATABASE");
        client = MongoClients.create(connString);
        database = client.getDatabase(db);
        users = database.getCollection("user_info");
    }

    public static boolean passwordAuth(String username, String password) {
        // MongoClient client = MongoClients.create(Dotenv.load().get("CONNECTION_STRING"));
        // MongoDatabase database = client.getDatabase(Dotenv.load().get("DATABASE"));
        // MongoCollection<Document> collection = database.getCollection("user_info");
        Document doc = users.find(Filters.eq("username", username)).first();
        if (doc == null) {
            return false;
        }
        return true;
    }

    public static String[] getUserInfo(String username) {
        
        Document doc = users.find(Filters.eq("username", username)).first();
        String[] data = new String[4];
        data[0] = doc.getString("username");
        data[1] = doc.getString("password");
        data[2] = doc.getString("fullname");
        data[3] = doc.getString("email");
        return data;
    }

    public static boolean isRegistered(String email, String username) {
        
        Document doc = users.find(Filters.eq("email", email)).first();
        if (doc == null) {
            return false;
        }
        doc = users.find(Filters.eq("username", username)).first();
        if (doc == null) {
            return false;
        }
        return true;
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

}
