package components;

import java.awt.Font;
import java.awt.Color;

import javax.swing.*;

import utils.Home;

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
    int GAP = 10;
    int labelWidth = 100;
    int startX = 70;
    int startY = 60;

    public RegisterPanel() {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 20));

        fullnameLabel = new JLabel("Fullname");
        fullnameLabel.setBounds(startX, startY, 100, 20);
        fullnameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(fullnameLabel);

        fullname = new JTextField(15);
        fullname.setBounds(startX + labelWidth + GAP, startY, 200, 20);
        fullname.setFont(new Font("Arial", Font.PLAIN, 14));
        fullname.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fullname.setBackground(Color.LIGHT_GRAY);
        fullname.setForeground(Color.BLACK);
        panel.add(fullname);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(startX, fullnameLabel.getY() + 30, 150, 20);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(usernameLabel);

        username = new JTextField(15);
        username.setBounds(startX + labelWidth + GAP, usernameLabel.getY(), 200, 20);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        username.setBackground(Color.LIGHT_GRAY);
        username.setForeground(Color.BLACK);
        panel.add(username);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(startX, usernameLabel.getY() + 30, 100, 20);
        emailLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(emailLabel);

        email = new JTextField(15);
        email.setBounds(startX + labelWidth + GAP, emailLabel.getY(), 200, 20);
        email.setFont(new Font("Arial", Font.PLAIN, 14));
        email.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        email.setBackground(Color.LIGHT_GRAY);
        email.setForeground(Color.BLACK);
        panel.add(email);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(startX, emailLabel.getY() + 30, 100, 20);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(startX + labelWidth + GAP, passwordLabel.getY(), 200, 20);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.setBackground(Color.LIGHT_GRAY);
        password.setForeground(Color.BLACK);
        panel.add(password);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(40, passwordLabel.getY() + 30, 150, 20);
        confirmPasswordLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(confirmPasswordLabel);

        confirmPassword = new JPasswordField(20);
        confirmPassword.setBounds(startX + labelWidth + GAP, confirmPasswordLabel.getY(), 200, 20);
        confirmPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        confirmPassword.setBackground(Color.LIGHT_GRAY);
        confirmPassword.setForeground(Color.BLACK);
        panel.add(confirmPassword);

        register = new JButton("Register");
        register.setBounds(170, 220, 100, 25);
        register.setForeground(Color.BLACK);
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(register);

        JButton loginLink = new JButton("Already have an account?");
        loginLink.setBounds(startX + 45, register.getY() + 30, 220, 20);
        loginLink.setBackground(Color.LIGHT_GRAY);
        loginLink.setForeground(Color.BLACK);
        loginLink.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(loginLink);

        register.addActionListener(l -> register());

        loginLink.addActionListener(l -> getLoginPanel());
    }

    public void getLoginPanel() {
        panel.setVisible(false);
        Home.addPanel(new LoginPanel().getPanel());
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
                panel.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        }
    }
}
