import java.sql.*;

import utils.Database;
import utils.Home;

public class App {
    public static void main(String[] args) throws Exception {
        Database db = new Database();

        
        String columns[] = {"Student ID", "Full Name", "Personal Email"};

        Home home = new Home();
        home.addTable("Students Table", columns, db.getTable("students"));
        home.show();

    }
}
