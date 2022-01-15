package components;

import javax.swing.*;

import utils.Home;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;

public class LoginPanel implements Panel {

    JPanel panel;
    JTextField username;
    JPasswordField password;
    JButton login;
    JButton register;
    JLabel usernameLabel;
    JLabel passwordLabel;
    int width = 400;
    int height = 250;

    public LoginPanel() {
        init();
    }

    public void init() {

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
        login.setBounds(160, 150, 100, 25);
        login.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(login);

        JLabel registerLink = new JLabel("Don't have an account?");
        registerLink.setBounds(120, 190, 200, 20);
        registerLink.setForeground(Color.LIGHT_GRAY);
        panel.add(registerLink);

        login.addActionListener(l -> login());
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getLinkedPanel(REGISTER);
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                registerLink.setForeground(Color.CYAN);
                registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                registerLink.setForeground(Color.LIGHT_GRAY);
                registerLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }

    public void getLinkedPanel(int type) {
        Home.removePanel(panel);
        if (type == REGISTER) {
            panel = new RegisterPanel().getPanel();
        }
        Home.addPanel(panel);
    }

    public void login() {
        if (username.getText().equals("") || String.copyValueOf(password.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = new User();
        user.login(username.getText(), String.copyValueOf(password.getPassword()));

        if (user.isVerified()) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            panel.setVisible(false);
            new ClockPanel();
            Home.addPanel(ClockPanel.getPanel());
            Home.addPanel(user.getPanel());
            // panel.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed");
        }
    }

}
