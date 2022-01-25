package utils;

import java.util.ArrayList;
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
    private static MongoCollection<Document> collection;

    public static void init() { 
        Dotenv env = Dotenv.load();
        String connString = env.get("CONNECTION_STRING");
        String db = env.get("DATABASE");
        client = MongoClients.create(connString);
        database = client.getDatabase(db);
        collection = database.getCollection("user_info");
    }

    public static boolean passwordAuth(String username, String password) {

        collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("username", username)).first();
        
        if (doc.get("password").equals(password)) {
            return true;
        }
        return false;
    }

    public static String[] getUserInfo(String username) {
        
        collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("username", username)).first();
        String[] data = new String[3];
        data[0] = doc.getString("username");
        data[1] = doc.getString("fullname");
        data[2] = doc.getString("email");
        return data;
    }

    public static boolean isRegistered(String email, String username) {
        
        collection = database.getCollection("user_info");
        Document doc = collection.find(Filters.eq("email", email)).first();
        if (doc != null) {
            return true;
        }
        doc = collection.find(Filters.eq("username", username)).first();
        if (doc != null) {
            return true;
        }
        return false;
    }

    public static boolean addNewUser(String username, String password, String fullname, String email) {
        
        collection = database.getCollection("user_info");
        Document doc = new Document("username", username)
                .append("password", password)
                .append("fullname", fullname)
                .append("email", email);
        try {
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void updateLogin(String username) {
    
        collection = database.getCollection("login_history");
        Document doc = collection.find(Filters.eq("username", username)).first();
        if (doc == null) {
            doc = new Document("username", username)
                    .append("last_login", new Date().getTime());
            collection.insertOne(doc);
        } else {
            collection.updateOne(Filters.eq("username", username), new Document("$set", new Document("last_login", new Date().getTime())));
        }
    }

    public static ArrayList<Assignment> getAssignments(String username) {
     
        init();
        collection = database.getCollection("assignments");
        Document doc = collection.find(Filters.eq("username", username)).first();
        if (doc != null) {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            ArrayList<Document> docs = (ArrayList<Document>) doc.get("assignment_list");
            for (Document d : docs) {
                assignments.add(new Assignment(d.getString("title"), d.getString("description"), d.getString("deadline").toString(), d.getBoolean("finished")));
            }
            return assignments;
        }
        return null;
    }

    public static void addAssignment(String username, String title, String dueDate, String description) {
        
        init();
        collection = database.getCollection("assignments");
        Document doc = collection.find(Filters.eq("username", username)).first();
        if (doc == null) {
            doc = new Document("username", username)
                    .append("assignment_list", new ArrayList<Document>());
            collection.insertOne(doc);
        }
        ArrayList<Document> docs = (ArrayList<Document>) doc.get("assignment_list");
        Document assignment = new Document("title", title)
                .append("description", description)
                .append("deadline", dueDate)
                .append("finished", false);
        docs.add(assignment);
        collection.updateOne(Filters.eq("username", username), new Document("$set", new Document("assignment_list", docs)));
    }
}
