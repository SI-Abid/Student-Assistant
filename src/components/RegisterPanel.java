package components;

import java.awt.Font;
import java.awt.Color;

import javax.swing.*;

public class RegisterPanel {
    JPanel panel;
    JTextField fullname;
    JTextField username;
    JTextField email;
    JPasswordField password;
    JPasswordField confirmPassword;
    JButton register;
    JLabel fullnameLabel;
    JLabel usernameLabel;
    JLabel emailLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;
    int width = 450;
    int height = 300;
    public RegisterPanel() {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 20));

        fullnameLabel = new JLabel("Fullname");
        fullnameLabel.setBounds(60, 80, 100, 20);
        fullnameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(fullnameLabel);

        fullname = new JTextField(15);
        fullname.setBounds(150, 80, 200, 20);
        fullname.setFont(new Font("Arial", Font.PLAIN, 14));
        fullname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fullname.setBackground(Color.LIGHT_GRAY);
        fullname.setForeground(Color.BLACK);
        panel.add(fullname);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(60, 110, 100, 20);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(usernameLabel);

        username = new JTextField(15);
        username.setBounds(150, 110, 200, 20);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        username.setBackground(Color.LIGHT_GRAY);
        username.setForeground(Color.BLACK);
        panel.add(username);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(60, 140, 100, 20);
        emailLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(emailLabel);

        email = new JTextField(15);
        email.setBounds(150, 140, 200, 20);
        email.setFont(new Font("Arial", Font.PLAIN, 14));
        email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        email.setBackground(Color.LIGHT_GRAY);
        email.setForeground(Color.BLACK);
        panel.add(email);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 170, 100, 20);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(150, 170, 200, 20);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.setBackground(Color.LIGHT_GRAY);
        password.setForeground(Color.BLACK);
        panel.add(password);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(60, 200, 150, 20);
        confirmPasswordLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(confirmPasswordLabel);

        confirmPassword = new JPasswordField(20);
        confirmPassword.setBounds(150, 200, 200, 20);
        confirmPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        confirmPassword.setBackground(Color.LIGHT_GRAY);
        confirmPassword.setForeground(Color.BLACK);
        panel.add(confirmPassword);

        register = new JButton("Register");
        register.setBounds(150, 230, 100, 20);
        register.setBackground(Color.LIGHT_GRAY);
        register.setForeground(Color.BLACK);
        panel.add(register);
        
        register.addActionListener(l -> register());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void register() {
        String fullname = this.fullname.getText();
        String username = this.username.getText();
        String email = this.email.getText();
        String password = new String(this.password.getPassword());
        String confirmPassword = new String(this.confirmPassword.getPassword());
        if (fullname.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match");
        } else {
            if (new User().register(fullname, username, password, email)) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        }
    }
}
