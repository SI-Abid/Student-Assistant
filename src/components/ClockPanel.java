package components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ClockPanel implements Panel {

    JLabel clock;
    JPanel panel;
    int width = 160;
    int height = 50;

    public ClockPanel() {
        init();
    }
    
    public void init() {

        panel = new JPanel();
        clock = new JLabel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.MAGENTA);

        clock.setBounds(0, 0, width, height);
        clock.setFont(new Font("Arial", Font.PLAIN, 27));
        clock.setForeground(Color.WHITE);
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setVerticalAlignment(JLabel.CENTER);
        panel.add(clock);
        // panel.setOpaque(false);

        new Clock(clock);

    }

    public JPanel getPanel() {
        return panel;
    }

    public void getLinkedPanel(Type type) {
        // TODO Auto-generated method stub
        
    }
}
