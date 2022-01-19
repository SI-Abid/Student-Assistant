import utils.Auth;
import utils.Home;


import javax.swing.JOptionPane;


public class Test {
   public static void main(String[] args) throws Exception {

        Auth.init();
        new Home();
        System.out.println("Hello World!");
    }

    public static void alert() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "You clicked yes button");
        } else {
            JOptionPane.showMessageDialog(null, "You clicked no button");
        }
    }
}