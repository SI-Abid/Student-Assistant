package utils;

import java.awt.Image;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.*;

import components.LoginPanel;

public class Home {

    private static JFrame frame;
    private static JLayeredPane layeredPane;
    private static int width = 800;
    private static int height = 600;

    public Home() {
        init();
    }

    private void init() {

        frame = new JFrame("Student Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // add image to frame background
        ImageIcon img = new ImageIcon("src/images/background.jpg");
        Image image = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        img = new ImageIcon(image);
        JLabel background = new JLabel(img);
        background.setBounds(0, 0, width, height);
        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, width, height);
        bgPanel.add(background);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, width, height);
        
        layeredPane.add(bgPanel, JLayeredPane.FRAME_CONTENT_LAYER);
        
        frame.add(layeredPane);
        
        addPanel(new LoginPanel().getPanel());

        frame.setVisible(true);
    }
    /**
     * Add panel at the center of the frame
     * @param panel
     */
    public static void addPanel(JPanel panel) {
        
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.moveToFront(panel);
        panel.setBounds(width / 2 - panel.getWidth() / 2, height / 2 - panel.getHeight() / 2, panel.getWidth(), panel.getHeight());
        panel.setOpaque(false);
    }
    /**
     * Add panel according to the position
     * @param panel
     * @param bounds
     */
    public static void addPanel(JPanel panel, Rectangle bounds) {
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.moveToFront(panel);
        panel.setBounds(bounds);
        panel.setOpaque(false);
    }
    /**
     * Remove panel from the frame
     * @param panel
     */
    public static void removePanel(JPanel panel) {
        panel.setVisible(false);
        layeredPane.remove(panel);
    }

    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    /**
     * remove all panels from default layer
     */
    public static void removeAllPanels() {
        Component[] c = layeredPane.getComponentsInLayer(JLayeredPane.DEFAULT_LAYER);
        for (Component component : c) {
            component.setVisible(false);
            layeredPane.remove(component);
        }
    }
    public void addMenu() {

    }
}
