import utils.Auth;
import utils.Home;


import javax.swing.JOptionPane;


public class Test {
   public static void main(String[] args) throws Exception {

        // MongoClient client = MongoClients.create(connString);

        // MongoDatabase database = client.getDatabase("Project_SA");

        // MongoCollection<Document> collection = database.getCollection("todo_list");

        // Document doc = new Document("user", "abid")
        //         .append("datetime", "2020-01-01")
        //         .append("tasklist", new ArrayList<String>() {
        //             {
        //                 add("task1");
        //                 add("task2");
        //                 add("task3");
        //             }
        //         });

        // collection.insertOne(doc);

        // Dotenv env = Dotenv.load();
        // String connString = env.get("CONNECTION_STRING");
        // String db = env.get("DATABASE");
        // MongoClient client = MongoClients.create(connString);
        // MongoDatabase database = client.getDatabase(db);
        // // export students collection
        // database.createCollection("students");
        // MongoCollection<Document> collection = database.getCollection("students");
        // Scanner sc = new Scanner(new FileInputStream("students.csv"));
        // String heading[] = sc.nextLine().split(",");
        
        // while (sc.hasNextLine()) {
        //     String line = sc.nextLine();
        //     String[] data = line.split(",");

        //     Document doc = new Document(heading[0], data[0])
        //             .append(heading[1], data[1])
        //             .append(heading[2], data.length > 2 ? data[2] : null);
        //     collection.insertOne(doc);
        // }
        new Auth();
        new Home();
        System.out.println("Hello World!");
    }

    public static void alert() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "You clicked yes button");
        } else {
            JOptionPane.showMessageDialog(null, "You clicked no button");
        }
    }
}