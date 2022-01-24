package components;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

import utils.Color;
import utils.Home;

public class DashBoard implements Panel{

    JPanel panel;
    int width = 160;
    int height = 200;
    int gap=10;

    public DashBoard() {
        init();
    }

    public void init() {

        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(null);
        panel.setBackground(Color.ORANGE);
        
        JButton examButton = new JButton("Exam");
        examButton.setBounds(gap, gap, width-gap*2, height/4-gap*2);
        examButton.setBackground(Color.RED);
        panel.add(examButton);

        JButton assignmentButton = new JButton("Assignment");
        assignmentButton.setBounds(gap, height/4+gap, width-gap*2, height/4-gap*2);
        assignmentButton.setBackground(Color.LIGHT_BLUE);
        panel.add(assignmentButton);

        JButton todoButton = new JButton("Todo");
        todoButton.setBounds(gap, height/2+gap, width-gap*2, height/4-gap*2);
        todoButton.setBackground(Color.YELLOW);
        panel.add(todoButton);

        JButton noteButton = new JButton("Note");
        noteButton.setBounds(gap, height/2 + height/4 +gap, width-gap*2, height/4-gap*2);
        noteButton.setBackground(Color.GREEN);
        panel.add(noteButton);

        examButton.addActionListener(l -> getLinkedPanel(Type.EXAM));
        assignmentButton.addActionListener(l -> getLinkedPanel(Type.ASSIGNMENT));
        todoButton.addActionListener(l -> getLinkedPanel(Type.TODO));
        noteButton.addActionListener(l -> getLinkedPanel(Type.NOTE));

    }

    @Override
    public void getLinkedPanel(Type type) {
        Home.removeContent();
        switch(type){
            case EXAM:
                panel = new ExamPanel().getPanel();
                break;
            case ASSIGNMENT:
                panel = new AssignmentPanel().getPanel();
                break;
            case TODO:
                panel = new TodoListPanel().getPanel();
                break;
            case NOTE:
                panel = new NotePanel().getPanel();
                break;
            default:
                break;
        }
        Home.addContent(panel);
    }

    @Override
    public JPanel getPanel() {
    
        return panel;
    }
    
}
