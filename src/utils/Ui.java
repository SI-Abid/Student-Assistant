package utils;

import javax.swing.*;
import javax.swing.JFrame;

public class Ui extends JFrame {
   // JTextField t1,t2;
    Ui(){
            JFrame f = new JFrame("Student Assistant");

            JLabel l1 = new JLabel("Enter your Email");
            l1.setBounds(100, 150, 100, 20);

            JLabel l2 = new JLabel("Enter Password");
            l2.setBounds(100, 250, 100, 30);

            JTextField t1 = new JTextField();
            t1.setBounds(70, 150, 100, 20);

            JTextField t2 = new JTextField();
            t2.setBounds(70, 250, 100, 30);

            JButton b = new JButton("Log In");
            b.setBounds(120, 350, 80, 40);

            f.add(l1);
            f.add(l2);
            f.add(t1);
            f.add(t2);
            f.add(b);
            f.setSize(500, 300);
            f.setLayout(null);
            f.setVisible(true);


        }


    }

    