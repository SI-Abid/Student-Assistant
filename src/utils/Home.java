package utils;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import components.LoginPanel;

public class Home {
    
    private JFrame frame;
    private int width = 700;
    private int height = 600;

    public Home() {

        frame = new JFrame("Student Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        
        // add image
        ImageIcon img = new ImageIcon("src/images/background.jpg");
        Image image = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        img = new ImageIcon(image);
        JLabel background = new JLabel(img);
        background.setBounds(0, 0, width, height);
        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, width, height);
        bgPanel.add(background);
        // frame.add(background);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, width, height);
        frame.add(layeredPane);
        
        // frame.add(tabbedPane);
        JPanel panel = new LoginPanel().getPanel();
        //////////////
        panel.setBounds(width/2-panel.getWidth()/2, height/2-panel.getHeight()/2-30, panel.getWidth(), panel.getHeight());
        panel.setOpaque(false);
        frame.add(panel);

        layeredPane.add(panel);
        layeredPane.add(bgPanel);

        frame.setVisible(true);
    }
    
}
