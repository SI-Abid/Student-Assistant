package components;

import javax.swing.*;

import utils.Home;

import java.awt.*;

public class LoginPanel {

    JPanel panel;
    JTextField username;
    JPasswordField password;
    JButton login;
    JLabel usernameLabel;
    JLabel passwordLabel;
    int width = 400;
    int height = 250;

    public LoginPanel() {

        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 20));

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(60, 80, 100, 20);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(usernameLabel);

        username = new JTextField(15);
        username.setBounds(150, 80, 200, 20);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        username.setBackground(Color.LIGHT_GRAY);
        username.setForeground(Color.BLACK);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 110, 100, 20);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(150, 110, 200, 20);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.setBackground(Color.LIGHT_GRAY);
        password.setForeground(Color.BLACK);
        panel.add(password);

        login = new JButton("Login");
        login.setBounds(160, 150, 100, 30);
        panel.add(login);

        login.addActionListener(l -> login());


    }

    public JPanel getPanel() {
        return panel;
    }

    public void login() {
        if(username.getText().equals("") || String.copyValueOf(password.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        User user = new User();
        user.login(username.getText(), String.copyValueOf(password.getPassword()));
        
        if(user.isVerified()) {
            JOptionPane.showMessageDialog(null, "Login Successful");
                panel.setVisible(false);
                new ClockPanel();
                Home.addPanel(ClockPanel.getPanel());
        //     panel.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed");
        }
    }
}
