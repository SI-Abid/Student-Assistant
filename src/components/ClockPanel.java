package components;

import java.awt.Font;
import java.awt.Color;
import javax.swing.*;

public class ClockPanel {

    static JLabel clock;
    static JPanel panel;
    int width = 400;
    int height = 250;

    public ClockPanel() {

        panel = new JPanel();
        clock = new JLabel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(new Color(170, 150, 230));

        // round panel corners
        clock.setBounds(width / 2 - 100, height / 2 - 50, 200, 100);
        clock.setFont(new Font("Arial", Font.PLAIN, 30));
        clock.setForeground(Color.WHITE);
        panel.add(clock);
        // panel.setOpaque(false);

        new Clock(clock);

    }

    public static JPanel getPanel() {
        return panel;
    }

    public void setTime(String time) {
        // panel = new JPanel();
        clock = new JLabel(time);
        // clock.setText(time);
    }

}
