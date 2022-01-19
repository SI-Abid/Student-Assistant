package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import utils.Home;

public class ProfilePanel implements Panel{

    JPanel panel;
    JLabel usernameLabel;
    JLabel fullnameLabel;
    JLabel emailLabel;
    JLabel userImage;
    JButton logout;
    String username;
    String fullname;
    String email;
    
    public ProfilePanel(String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        init();
    }

    private void init() {
        
        panel = new JPanel();
        panel.setSize(200, 200);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        
        // add user image
        userImage = new JLabel();
        userImage.setBounds(50, 20, 100, 100);
        ImageIcon imageIcon = new ImageIcon("src/images/user.png");
        Image img = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        userImage.setIcon(new ImageIcon(img));
        panel.add(userImage);

        // add username
        usernameLabel = new JLabel(username);
        usernameLabel.setBounds(10, 120, 180, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        // align text to the middle
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        panel.add(usernameLabel);

        // add fullname
        fullnameLabel = new JLabel(fullname);
        fullnameLabel.setBounds(10, 150, 180, 20);
        fullnameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fullnameLabel.setHorizontalAlignment(JLabel.CENTER);
        fullnameLabel.setForeground(Color.WHITE);
        panel.add(fullnameLabel);

        // add logout button
        logout = new JButton("LOGOUT");
        logout.setBounds(60, 180, 80, 20);
        logout.setFont(new Font("Arial", Font.BOLD, 12));
        logout.setBackground(Color.YELLOW);
        logout.setForeground(Color.BLACK);
        logout.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(logout);

        logout.addActionListener(l -> getLinkedPanel(Type.LOGIN));
        
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void getLinkedPanel(Type type) {
        Home.removeAllPanels();
        if(type == Type.LOGIN) {
            Home.addPanel(new LoginPanel().getPanel());
        }
    }
    
}
