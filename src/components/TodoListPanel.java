package components;

import javax.swing.JPanel;

import utils.Color;

public class TodoListPanel implements Panel {

    JPanel panel;
    int width = 640;
    int height = 600;
    public TodoListPanel() {
        init();
    }

    @Override
    public void init() {
        
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.YELLOW);
        
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
