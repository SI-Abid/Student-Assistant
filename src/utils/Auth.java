package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import io.github.cdimascio.dotenv.Dotenv;

public class Auth {

    public static boolean passwordAuth(String username, String password) {
        MongoClient client = MongoClients.create(Dotenv.load().get("CONNECTION_STRING"));
        MongoDatabase database = client.getDatabase(Dotenv.load().get("DATABASE"));
        MongoCollection<Document> collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("username", username)).first();
        if (doc == null) {
            return false;
        }
        return true;
    }

    public static String[] getUserInfo(String username) {
        MongoClient client = MongoClients.create(Dotenv.load().get("CONNECTION_STRING"));
        MongoDatabase database = client.getDatabase(Dotenv.load().get("DATABASE"));
        MongoCollection<Document> collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("username", username)).first();
        String[] data = new String[4];
        data[0] = doc.getString("username");
        data[1] = doc.getString("password");
        data[2] = doc.getString("fullname");
        data[3] = doc.getString("email");
        return data;
    }

    public static boolean isRegistered(String email) {
        MongoClient client = MongoClients.create(Dotenv.load().get("CONNECTION_STRING"));
        MongoDatabase database = client.getDatabase(Dotenv.load().get("DATABASE"));
        MongoCollection<Document> collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("email", email)).first();
        if (doc == null) {
            return false;
        }
        return true;
    }

}
