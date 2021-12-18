package utils;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Home {
    JFrame frame;
    public Home() {
        frame = new JFrame("Home");
        frame.setSize(650, 500);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void show() {
        frame.setVisible(true);
    }
    public void addTable(String title, String[] columns, ArrayList<String[]> data) {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(title);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(label);

        JTable table = new JTable(data.toArray(new String[data.size()][]), columns);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(280);
        table.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        panel.add(scrollPane);
        frame.add(panel);
    }
}
