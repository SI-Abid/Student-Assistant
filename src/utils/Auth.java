package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

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
            collection.updateOne(Filters.eq("username", username),
                    new Document("$set", new Document("last_login", new Date().getTime())));
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Assignment> getAssignments(String username) {

        init();
        collection = database.getCollection("assignments");
        Document doc = collection.find(Filters.eq("username", username)).first();
        if (doc != null) {
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            ArrayList<Document> docs = (ArrayList<Document>) doc.get("assignment_list");
            for (Document d : docs) {
                assignments.add(new Assignment(d.getString("title"), d.getString("description"),
                        d.getString("deadline").toString(), d.getBoolean("finished")));
            }
            return assignments;
        }
        Assignment assignment = new Assignment("Demo Assignment",
                "This is a demo assignment\nDelete this and create new assignment", "2020-01-01", false);
        // assignments.add(assignment);
        User.addAssignment(assignment);
        return User.assignments;
    }

    // public static void addAssignment(String username, Assignment as) {

    // init();
    // collection = database.getCollection("assignments");
    // Document doc = collection.find(Filters.eq("username", username)).first();
    // if (doc == null) {
    // doc = new Document("username", username)
    // .append("assignment_list", new ArrayList<Document>());
    // collection.insertOne(doc);
    // }
    // ArrayList<Document> docs = (ArrayList<Document>) doc.get("assignment_list");
    // Document assignment = new Document("title", as.getTitle())
    // .append("description", as.getDescription())
    // .append("deadline", as.getDueDate())
    // .append("finished", false);
    // docs.add(assignment);
    // collection.updateOne(Filters.eq("username", username), new Document("$set",
    // new Document("assignment_list", docs)));
    // }

    public static void update() {
        init();
        collection = database.getCollection("assignments");
        ArrayList<Document> docs = new ArrayList<Document>();
        for (Assignment as : User.assignments) {
            Document assignment = new Document("title", as.getTitle())
                    .append("description", as.getDescription())
                    .append("deadline", as.getDueDate())
                    .append("finished", as.isCompleted());
            docs.add(assignment);
        }
        if (collection.find(Filters.eq("username", User.getUsername())).first() == null) {
            Document doc = new Document("username", User.getUsername())
                    .append("assignment_list", docs);
            collection.insertOne(doc);
        } else {
            collection.updateOne(Filters.eq("username", User.getUsername()),
                    new Document("$set", new Document("assignment_list", docs)));
        }

    }

    public static boolean isValidEmail(String email) {
        return Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .find();
    }

    public static boolean isStrongPassword(String password) {
        return Pattern
                .compile("^\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[A-Z])(?=\\S*[a-z])(?=\\S*[!@#$%^&*?])\\S*$")
                .matcher(password)
                .find();
    }
}
