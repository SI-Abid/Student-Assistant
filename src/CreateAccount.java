import javax.swing.*;
import java.awt.Font;
import java.awt.*;

class CA extends JFrame {

    Container c;
    JLabel title;
    JLabel lfname;
    JTextField tfname;
    JLabel luname;
    JTextField tuname;
    JLabel lemail;
    JTextField temail;
    JLabel lpass;
    JTextField tpass;
    JLabel lcpass;
    JTextField tcpass;
    JButton bregi;

    CA (){
        setTitle("Registration Form");
        setSize(400, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Create Account");
        title.setBounds(100, 20, 200, 50);
        Font f = new Font ("Arial",Font.BOLD,20);
        title.setFont(f);
        title.setForeground(Color.black);
        c.add(title);
 
        lfname = new JLabel("Full Name :");
        lfname.setBounds(30, 80, 80, 20);
        c.add(lfname);

        tfname = new JTextField();
        tfname.setBounds(170, 80, 150, 20);
        c.add(tfname);

        luname = new JLabel("Username :");
        luname.setBounds(30, 120, 80, 20);
        c.add(luname);

        tuname = new JTextField();
        tuname.setBounds(170, 120, 150, 20);
        c.add(tuname);

        lemail = new JLabel("Email :");
        lemail.setBounds(30, 160, 80, 20);
        c.add(lemail);

        temail = new JTextField();
        temail.setBounds(170, 160, 150, 20);
        c.add(temail);

        lpass = new JLabel("Password :");
        lpass.setBounds(30, 200, 80, 20);
        c.add(lpass);

        tpass = new JTextField();
        tpass.setBounds(170, 200, 150, 20);
        c.add(tpass);

        lcpass = new JLabel("Confirm Password :");
        lcpass.setBounds(30, 240, 130, 20);
        c.add(lcpass);

        tcpass = new JTextField();
        tcpass.setBounds(170, 240, 150, 20);
        c.add(tcpass);

        bregi = new JButton("Register");
        bregi.setForeground(Color.white);
        bregi.setBounds(130,280,100,30);
        bregi.setBackground(Color.BLUE);
        c.add(bregi);

        setVisible(true);

    }

}

public class CreateAccount {
    public static void main(String[] args) {
            new CA();
    } 
}