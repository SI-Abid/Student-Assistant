package utils;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private String DB_URL;
    private String DB_USER;
    private String DB_PASS;
    
    public Database() {
        this(true);   
    }
    
    public Database(boolean isTest) {
        
        DB_URL = Secret.getDB_URL();
        DB_USER = Secret.getDB_USER();
        DB_PASS = Secret.getDB_PASS();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        
        if(isTest)
            return;

        try {
            
            System.out.println("Connecting to database...");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = con.createStatement();

            // delete students tables
            System.out.println("Deleting all tables...");
            stmt.executeUpdate("DROP TABLE IF EXISTS students");

            // create table students if not exists
            System.out.println("Creating table students if not exists...");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (id VARCHAR(10) NOT NULL, name VARCHAR(30), email VARCHAR(40), PRIMARY KEY (id))");

            // insert data from csv file
            System.out.println("Inserting data from csv file...");
            stmt.executeUpdate("LOAD DATA LOCAL INFILE 'students.csv' INTO TABLE students FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 LINES");

            // select all data
            System.out.println("Selecting all data...");
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println(rs.getString("id") + ", " + rs.getString("name") + ", " + rs.getString("email"));
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public ArrayList<String[]> getTable(String tableName) {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            ArrayList<String[]> data = new ArrayList<String[]>();
            while (rs.next()) {
                String[] row = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = rs.getString(i);
                }
                data.add(row);
            }
            rs.close();
            stmt.close();
            con.close();
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * 
     * @param tableName
     * @param attr
     * @param value
     * @param id
     * 
     * @return true if update successful
     * 
     */
    public boolean updateAttr(String tableName, String attr, String value, String id) {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE " + tableName + " SET " + attr + " = '" + value + "' WHERE id = '" + id + "'");
            stmt.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}