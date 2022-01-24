package components;

import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import utils.Home;
import utils.Color;

public class ProfilePanel implements Panel {

    JPanel panel;
    JLabel usernameLabel;
    JLabel fullnameLabel;
    JLabel emailLabel;
    JLabel userImage;
    JButton logout;
    User user;
    int width = 160;
    int height = 220;
    
    public ProfilePanel(User user) {
        this.user = user;
        init();
    }

    public void init() {
        
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_PURPLE);
        
        // add user image
        userImage = new JLabel();
        userImage.setBounds(30, 20, 100, 100);
        ImageIcon imageIcon = new ImageIcon("src/images/user.png");
        Image img = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        userImage.setIcon(new ImageIcon(img));
        panel.add(userImage);

        // add username
        usernameLabel = new JLabel(user.Username);
        usernameLabel.setBounds(10, 120, 140, 20);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        panel.add(usernameLabel);

        // add fullname
        fullnameLabel = new JLabel(user.FullName);
        fullnameLabel.setBounds(10, 150, 140, 20);
        fullnameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fullnameLabel.setHorizontalAlignment(JLabel.CENTER);
        fullnameLabel.setForeground(Color.WHITE);
        panel.add(fullnameLabel);

        // add logout button
        logout = new JButton("LOGOUT");
        logout.setBounds(40, 180, 80, 20);
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
            user=null;
            Home.addPanel(new LoginPanel().getPanel());
        }
    }
    
}
