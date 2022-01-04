package utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

public class Auth {

    private static final String connString = "mongodb+srv://abid:siahio5323@cluster0.2sdvc.mongodb.net/Project_SA?retryWrites=true&w=majority";
    private static final String apiKey = "927cc09a-9c54-49da-be04-4a34faa62a5f";
    private static ConnectionString conn;
    private static MongoClientSettings settings;
    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private static Document doc;
    private static final String URL_endpoint = "https://data.mongodb-api.com/app/data-ywkxv/endpoint/data/beta";
    private static final String URL_apiKey = "nh1yg8GjDtMxFEQTwSJqlffyeOAFVjFeBHHar9WFdjiVJgy1RhLUdYYg1mBzfBXH";

    public static Document getDocs(String database, String collection, String field, String value) {
        try{
            
            return MongoClients.create(new ConnectionString(connString))
                .getDatabase(database)
                .getCollection(collection)
                .find(Filters.eq(field, value))
                .first();
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean passwordAuth(String username, String password) {
        
        doc = getDocs("Project_SA", "user_info", "username", username);
        if (doc == null) {
            return false;
        }
        String dbPassword = doc.getString("password");
        System.out.println("--------------------------------------------------");
        System.out.println(dbPassword);
        System.out.println("--------------------------------------------------");
        
        if (dbPassword.equals(password)) {
            return true;
        }
        
        return false;
    }

    public static String[] getUserInfo(String username) {
        
        doc = getDocs("Project_SA", "user_info", "username", username);

        if(doc == null)
            return null;

        String[] data = new String[4];
        data[0] = doc.getString("username");
        data[1] = doc.getString("password");
        data[2] = doc.getString("fullname");
        data[3] = doc.getString("email");
        return data;
    }

    public static boolean isRegistered(String email) {
        
        doc = getDocs("Project_SA", "user_info", "email", email);
        if (doc == null) {
            return false;
        }
        return true;
    }

}
