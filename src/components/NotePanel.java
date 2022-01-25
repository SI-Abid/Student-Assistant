package components;

import javax.swing.JPanel;

import utils.Color;

public class NotePanel implements Panel {

    JPanel panel;
    
    public NotePanel() {
        init();
    }

    @Override
    public void getLinkedPanel(Type type) {
        
        
    }

    @Override
    public JPanel getPanel() {
        
        return panel;
    }

    @Override
    public void init() {
        
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        
        
    }
    
}
