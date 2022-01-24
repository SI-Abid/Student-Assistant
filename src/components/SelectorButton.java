package components;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import utils.Color;

import java.awt.Cursor;
import java.awt.event.*;

public class SelectorButton extends JButton {

    public SelectorButton(String text) {
        super(text);
    }

    public void addActionListener(ActionListener listener) {
        addActionListener(listener);
    }

    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseClicked(MouseEvent e) {
        setBackground(Color.GREEN);
    }

}
