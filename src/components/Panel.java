package components;

import javax.swing.JPanel;

public interface Panel {
    int width = 630;
    int height = 555;
    void init();
    JPanel getPanel();
    void getLinkedPanel(Type type);
}
