package components;

import java.awt.Color;

import javax.swing.JPanel;

public class ExamPanel implements Panel{

    JPanel panel;
    int width = 640;
    int height = 600;
    public ExamPanel() {
        init();
    }

    public void init() {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.RED);

    }

    @Override
    public void getLinkedPanel(Type type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public JPanel getPanel() {
        // TODO Auto-generated method stub
        return panel;
    }
    
}
