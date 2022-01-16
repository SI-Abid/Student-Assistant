package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicPanelUI;

import utils.Home;

public class ProfilePanel implements Panel{

    JPanel panel;
    JLabel usernameLabel;
    JLabel fullnameLabel;
    JLabel emailLabel;
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
        panel.setSize(250, 250);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255));
        panel.setUI(new BasicPanelUI());
        
        usernameLabel = new JLabel(username);
        usernameLabel.setBounds(60, 60, 100, 20);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(usernameLabel);
        
        fullnameLabel = new JLabel(fullname);
        fullnameLabel.setBounds(60, 90, 200, 20);
        fullnameLabel.setForeground(Color.WHITE);
        fullnameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(fullnameLabel);
        
        emailLabel = new JLabel(email);
        emailLabel.setBounds(60, 120, 200, 20);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(emailLabel);
        
        logout = new JButton("Logout");
        logout.setBounds(60, 150, 100, 20);
        logout.setForeground(Color.BLACK);
        logout.setFont(new Font("Arial", Font.PLAIN, 15));
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
