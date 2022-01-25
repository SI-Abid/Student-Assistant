package utils;

import java.util.ArrayList;

import javax.swing.*;

import components.ProfilePanel;

public class User {

    public static String FullName;
    public static String Username;
    public static String Email;
    public static boolean isLoggedIn=false;
    public static ArrayList<Assignment> assignments;
    public static ArrayList<Object> exams;
    public static ArrayList<Object> todos;
    public static ArrayList<Object> notes;
    
    public void login(String username, String password) {

        isLoggedIn=Auth.passwordAuth(username, password);
        
        if(isLoggedIn) {
            String[] data = Auth.getUserInfo(username);
            Username = data[0];
            FullName = data[1];
            Email = data[2];
            Auth.updateLogin(username);
            assignments = Auth.getAssignments(username);
        }
    }

    public boolean register(String fullName, String username, String password, String email) {

        if (Auth.isRegistered(email, username)) {
            return false;
        }

        return Auth.addNewUser(username, password, fullName, email);
    }

    public boolean isVerified() {
        return isLoggedIn;
    }

    public JPanel getPanel() {
        return new ProfilePanel(this).getPanel();
    }
    
    public static String getFullName() {
        return FullName;
    }

    public static String getUsername() {
        return Username;
    }

    public static void addAssignment(Assignment assignment) {
        if(assignments==null) {
            assignments=new ArrayList<Assignment>();
        }
        assignments.add(assignment);
        // Auth.addAssignment(Username, assignment);
    }
    public static void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
        // Auth.removeAssignment(Username, assignment);
    }
}
