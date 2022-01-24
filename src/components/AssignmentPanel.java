package components;

import javax.swing.JPanel;

import utils.Color;

public class AssignmentPanel implements Panel {

    JPanel panel;
    int width = 640;
    int height = 600;

    public AssignmentPanel() {
        init();
    }
    
    @Override
    public void init() {
        // TODO Auto-generated method stub
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_BLUE);
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
