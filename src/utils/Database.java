package utils;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class is used to connect to the database and execute queries.
 * 
 * @author Saiham Islam
 * @version 1.0
 * 
 */

public class Database {

    private final String DB_URL = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6459007";
    private final String DB_USER = "sql6459007";
    private final String DB_PASS = "bAXluqI4Sv";
    private Statement statement;

    public Database() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected void init() {

        // delete("students", new String[] { "2123456789" });
        // insert("students", new String[] { "2123456789", "Mahir Afnan",
        // "outsider_101@unknown.net" });
        // update("students", "id", "2123456789", "name", "Sharif Adnan");
        // for (String[] row : getTable("students")) {

        //     System.out.print(String.format("|%12s |", row[0]));
        //     System.out.print(String.format("|%32s |", row[1]));
        //     System.out.println(String.format("|%27s |", row[2]));
        //     for (int i = 0; i < 80; i++)
        //         System.out.print("-");
        //     System.out.println();
        // }
        // create users table if not exists
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (username VARCHAR(20), password VARCHAR(30), fullname VARCHAR(50), email VARCHAR(50), PRIMARY KEY (username))");
            insert("users", new String[] { "root", "toor", "Admin Root", "admin@su.org" });
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * 
     * @param tableName
     * @return the table as an array of strings
     */
    public ArrayList<String[]> getTable(String tableName) {

        ArrayList<String[]> table = new ArrayList<String[]>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            while (resultSet.next()) {

                String[] row = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < row.length; i++) {

                    row[i] = resultSet.getString(i + 1);
                }
                table.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 
     * @param tableName
     * @param values
     * @return true if the insertion was successful
     */
    public boolean insert(String tableName, String[] values) {

        String query = "INSERT INTO " + tableName + " VALUES (";

        for (int i = 0; i < values.length; i++) {
            query += "'" + values[i] + "'";

            if (i != values.length - 1) {
                query += ", ";
            }
        }
        query += ")";
        // System.out.println(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method is used to delete rows from the table.
     * 
     * @param tableName The name of the table.
     * @param values    Rows with the values to be deleted.
     * @param field     The field to be used to find matching records.
     * @return true if the deletion was successful.
     */
    public boolean delete(String tableName, String field, String[] values) {

        String query = "DELETE FROM " + tableName + " WHERE ";

        for (int i = 0; i < values.length; i++) {
            query += field + " = " + values[i];

            if (i != values.length - 1) {
                query += " OR ";
            }
        }
        // System.out.println(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 
     * updates the table with the given column name and column value
     * 
     * @param tableName
     * @param columnName
     * @param columnValue
     * @param updateColumnName
     * @param updateColumnValue
     * @return true if the update was successful
     * 
     */
    public boolean update(String tableName, String columnName, String columnValue, String updateColumnName,
            String updateColumnValue) {

        String query = "UPDATE " + tableName + " SET " + updateColumnName + " = '" + updateColumnValue + "' WHERE "
                + columnName + " = '" + columnValue + "'";
        // System.out.println(query);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 
     * @param username
     * @return the password of the user if the user exists, otherwise returns null
     */
    public String getPassword(String username) {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            ResultSet rs = statement.executeQuery("SELECT password FROM users WHERE username = '" + username + "'");
            if (rs.next()) {
                String password = rs.getString("password");
                rs.close();
                return password;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String[] getUserInfo(String username) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
            if (rs.next()) {
                String[] userInfo = new String[4];
                userInfo[0] = rs.getString("username");
                userInfo[1] = rs.getString("password");
                userInfo[2] = rs.getString("fullname");
                userInfo[3] = rs.getString("email");
                rs.close();
                return userInfo;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean isRegistered(String email) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if (rs.next()) {
                rs.close();
                return true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}