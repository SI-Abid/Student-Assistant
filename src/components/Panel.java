package components;

import javax.swing.JPanel;

public interface Panel {
    void init();
    JPanel getPanel();
    void getLinkedPanel(Type type);
}
