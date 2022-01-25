package components;

import utils.Color;

import javax.swing.*;

public class ExamPanel implements Panel{

    JPanel panel;
    public ExamPanel() {
        init();
    }

    public void init() {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.RED);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

    }

    @Override
    public void getLinkedPanel(Type type) {
        
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
    
}
