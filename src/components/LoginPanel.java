package components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;

import utils.Auth;
import utils.Home;
import utils.Color;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
// import java.awt.Color;
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

    private void init() {

        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_PURPLE);
        panel.setUI(new BasicPanelUI());

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(60, 60, 100, 20);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(usernameLabel);

        username = new JTextField(15);
        username.setBounds(150, 60, 200, 20);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        username.setMargin(new Insets(0, 10, 0, 10));
        username.setBackground(Color.LIGHT_GRAY);
        username.setForeground(Color.BLACK);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 90, 100, 20);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(150, 90, 200, 20);
        password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        password.setBackground(Color.LIGHT_GRAY);
        password.setForeground(Color.BLACK);
        panel.add(password);

        login = new JButton("Sign In");
        login.setBounds(150, 130, 120, 25);
        login.setFont(new Font("Arial", Font.BOLD, 15));
        login.setBackground(Color.LIGHT_GREEN);
        login.setForeground(Color.WHITE);
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(login);

        JLabel registerLink = new JLabel("Don't have an account?");
        registerLink.setBounds(125, 170, 200, 20);
        registerLink.setForeground(Color.WHITE);
        panel.add(registerLink);

        login.addActionListener(l -> login());
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setCursor(new Cursor(Cursor.HAND_CURSOR));
                login.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                login.setBackground(Color.LIGHT_GREEN);
            }
        });

        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getLinkedPanel(Type.REGISTER);
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                registerLink.setForeground(Color.CYAN);
                registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                registerLink.setForeground(Color.WHITE);
                registerLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }

    public void getLinkedPanel(Type type) {
        Home.removePanel(panel);
        if (type == Type.REGISTER) {
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

        Auth.init();
        User user = new User();
        user.login(username.getText(), String.copyValueOf(password.getPassword()));

        if (user.isVerified()) {
            
            Home.removePanel(panel);
            new ClockPanel();
            // Home.addPanel(ClockPanel.getPanel());
            JPanel profile = user.getPanel();
            Home.addPanel(profile, new Rectangle(Home.getWidth() - profile.getWidth(), 0, profile.getWidth(), profile.getHeight()));
            
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed");
        }
    }

}
