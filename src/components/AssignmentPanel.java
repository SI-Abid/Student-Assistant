package components;

import java.awt.Font;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import utils.Assignment;
import utils.Color;
import utils.User;

public class AssignmentPanel implements Panel {

    JPanel panel;

    public AssignmentPanel() {
        init();
    }
    
    @Override
    public void init() {
    
        JPanel panel = new JPanel();
        panel.setSize(width, height-30);
        panel.setBounds(0, 0, width, height);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        ParallelGroup hGroup = layout.createParallelGroup(Alignment.LEADING);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addGroup(hGroup)
        );
        
        SequentialGroup vGroup = layout.createSequentialGroup().addContainerGap();
        layout.setVerticalGroup(vGroup);

        panel.setBackground(Color.LIGHT_BLUE);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, width, height-30);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        // scrollPane.getVerticalScrollBar().setValue(0);
        // scrollPane.repaint();
        // scrollPane.revalidate();
        
        for(Assignment assignment : User.assignments) {
            
            if(assignment==null) {
                continue;
            }

            loadAssignment(assignment, hGroup, vGroup);
            
        }

        this.panel = new JPanel();
        this.panel.setSize(width, height);
        this.panel.setLayout(null);
        this.panel.setBackground(Color.LIGHT_BLUE);
        this.panel.setOpaque(false);
        this.panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.panel.add(scrollPane);
        JButton addButton = new JButton("Add");
        addButton.setBounds(width-100, height-30, 100, 30);
        addButton.setBackground(Color.LIGHT_BLUE);
        addButton.setForeground(Color.BLACK);
        addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.panel.add(addButton);

        addButton.addActionListener(l -> {
            JFrame frame = new JFrame("Add Assignment");
            frame.setSize(400, 400);
            frame.setLayout(null);
            frame.setBackground(Color.LIGHT_BLUE);
            frame.setLocationRelativeTo(this.panel);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel titleLabel = new JLabel("Title");
            titleLabel.setBounds(10, 10, 100, 30);
            JTextField title = new JTextField();
            title.setBounds(10, 40, 380, 30);
            JLabel dueDateLabel = new JLabel("Due Date");
            dueDateLabel.setBounds(10, 80, 100, 30);
            JTextField dueDate = new JTextField("YYYY-MM-DD");
            dueDate.setBounds(10, 110, 380, 30);
            JLabel descriptionLabel = new JLabel("Description");
            descriptionLabel.setBounds(10, 150, 100, 30);
            JTextArea description = new JTextArea();
            description.setBounds(10, 180, 380, 120);
            JButton add = new JButton("Add");
            add.setBounds(10, 320, 100, 30);
            add.setBackground(Color.LIGHT_BLUE);
            add.setForeground(Color.BLACK);

            frame.add(titleLabel);
            frame.add(title);
            frame.add(dueDateLabel);
            frame.add(dueDate);
            frame.add(descriptionLabel);
            frame.add(description);
            frame.add(add);

            add.addActionListener(l2 -> {
                Assignment as = new Assignment(title.getText(), description.getText(), dueDate.getText(), false);
                User.addAssignment(as);
                loadAssignment(as, hGroup, vGroup);
                frame.dispose();
                
            });
        });

        }
        
    @Override
    public void getLinkedPanel(Type type) {
        
        
    }

    @Override
    public JPanel getPanel() {
    
        return panel;
    }

    public static void loadAssignment(Assignment assignment,ParallelGroup hGroup, SequentialGroup vGroup) {
        
        JPanel assignmentPanel = new JPanel();
        assignmentPanel.setSize(width, 60);
        assignmentPanel.setLayout(null);
        
        assignmentPanel.setBackground(Color.LIGHT_BLUE);
        assignmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JLabel title = new JLabel(assignment.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setBounds(10, 10, width-20, 20);
        title.setForeground(Color.BLACK);
        assignmentPanel.add(title);
        
        JLabel dueDate = new JLabel(assignment.getDueDate());
        dueDate.setFont(new Font("Arial", Font.PLAIN, 12));
        dueDate.setBounds(10, 30, width-20, 20);
        dueDate.setForeground(Color.BLACK);
        assignmentPanel.add(dueDate);
        
        JTextArea description = new JTextArea(assignment.getDescription());
        description.setFont(new Font("Arial", Font.PLAIN, 12));
        description.setBounds(10, 50, width-150, 40);
        description.setOpaque(false);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setForeground(Color.BLACK);
        assignmentPanel.add(description);

        JLabel status = new JLabel(assignment.isCompleted() ? "Completed" : "Not Completed");
        status.setFont(new Font("Arial", Font.BOLD, 12));
        status.setBounds(width-170, 75, 120, 20);
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        status.setForeground(status.getText().equals("Completed") ? Color.GREEN : Color.RED);
        assignmentPanel.add(status);
        
        vGroup.addComponent(assignmentPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE);
        hGroup.addComponent(assignmentPanel);

        assignmentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1) {
                    if(assignment.isCompleted()) {
                        assignment.setCompleted(false);
                        status.setText(" Not Completed");
                        status.setForeground(Color.RED);
                    } else {
                        assignment.setCompleted(true);
                        status.setText(" Completed");
                        status.setForeground(Color.GREEN);
                    }
                } else if(e.getButton()==MouseEvent.BUTTON3) {
                    JFrame frame = new JFrame("Edit Assignment");
                    frame.setSize(400, 400);
                    frame.setLayout(null);
                    frame.setBackground(Color.LIGHT_BLUE);
                    frame.setLocationRelativeTo(assignmentPanel);
                    frame.setResizable(false);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JLabel titleLabel = new JLabel("Title");
                    titleLabel.setBounds(10, 10, 100, 30);
                    JTextField title = new JTextField(assignment.getTitle());
                    title.setBounds(10, 40, 380, 30);
                    JLabel dueDateLabel = new JLabel("Due Date");
                    dueDateLabel.setBounds(10, 80, 100, 30);
                    JTextField dueDate = new JTextField(assignment.getDueDate());
                    dueDate.setBounds(10, 110, 380, 30);
                    JLabel descriptionLabel = new JLabel("Description");
                    descriptionLabel.setBounds(10, 150, 100, 30);
                    JTextArea description = new JTextArea(assignment.getDescription());
                    description.setBounds(10, 180, 380, 120);
                    JButton edit = new JButton("Save");
                    edit.setBounds(10, 320, 100, 30);
                    edit.setBackground(Color.LIGHT_BLUE);
                    edit.setForeground(Color.BLACK);
                    JButton delete = new JButton("Delete");
                    delete.setBounds(120, 320, 100, 30);
                    delete.setBackground(Color.LIGHT_RED);
                    delete.setForeground(Color.BLACK);

                    frame.add(titleLabel);
                    frame.add(title);
                    frame.add(dueDateLabel);
                    frame.add(dueDate);
                    frame.add(descriptionLabel);
                    frame.add(description);
                    frame.add(edit);
                    frame.add(delete);

                    edit.addActionListener(l2 -> {
                        assignment.setTitle(title.getText());
                        assignment.setDueDate(dueDate.getText());
                        assignment.setDescription(description.getText());
                        ((JLabel)assignmentPanel.getComponents()[0]).setText(title.getText());
                        ((JLabel)assignmentPanel.getComponents()[1]).setText(dueDate.getText());
                        ((JTextArea)assignmentPanel.getComponents()[2]).setText(description.getText());
                        frame.dispose();
                    });

                    delete.addActionListener(l2 -> {
                        User.removeAssignment(assignment);
                        assignmentPanel.setVisible(false);
                        frame.dispose();
                    });
                }
            }
        });
    }
    
}
