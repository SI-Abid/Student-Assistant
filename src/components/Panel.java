package components;

import javax.swing.JPanel;

public interface Panel {
    int LOGIN = 0;
    int REGISTER = 1;
    int HOME = 2;
    int PROFILE = 3;
    int LOGOUT = 4;
    void init();
    JPanel getPanel();
    void getLinkedPanel(int type);
}
