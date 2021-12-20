package components;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class Button extends JButton {

    JTextField textField;

    public Button(String text, JTextField textField) {
        super(text);
        this.textField = textField;
    }

    public void addActionListener(ActionListener l) {

        if (getText().equals("DEL")) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        } else {

            super.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + getText());
                }
            });
        }
    }
}
