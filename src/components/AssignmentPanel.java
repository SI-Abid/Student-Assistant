package components;

import java.awt.Font;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

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
        // layout.setVerticalGroup(
        //     layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        //     .addGroup(group)
        // );
        ParallelGroup hGroup = layout.createParallelGroup(Alignment.TRAILING, false);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(hGroup)
        );
        SequentialGroup vGroup = layout.createSequentialGroup().addContainerGap();
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vGroup)
        );
        // jPanel1.setLayout(layout);
        // layout.setHorizontalGroup(
        //     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(layout.createSequentialGroup()
        //         .addContainerGap()
        //         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //             .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        //             .addGroup(layout.createSequentialGroup()
        //                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
        //                     .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        //                     .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        //                     .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        //                 .addGap(0, 0, Short.MAX_VALUE)))
        //         .addContainerGap())
        // );
        panel.setBackground(Color.LIGHT_BLUE);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, width, height-30);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        for(Assignment assignment : User.assignments) {
            
            if(assignment==null) {
                continue;
            }
            JPanel assignmentPanel = new JPanel();
            assignmentPanel.setSize(width, 80);
            GroupLayout assignmentLayout = new GroupLayout(assignmentPanel);
            assignmentLayout.setHorizontalGroup(
                assignmentLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            assignmentLayout.setVerticalGroup(
                assignmentLayout.createParallelGroup(Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
            );
            assignmentPanel.setLayout(assignmentLayout);
            
            assignmentPanel.setBackground(Color.LIGHT_BLUE);
            // assignmentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            
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
            
            JLabel description = new JLabel(assignment.getDescription());
            description.setFont(new Font("Arial", Font.PLAIN, 12));
            description.setBounds(10, 50, width-20, 20);
            description.setForeground(Color.BLACK);
            assignmentPanel.add(description);
            
            vGroup.addComponent(assignmentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
            hGroup.addComponent(assignmentPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
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
                User.addAssignment(title.getText(), dueDate.getText(), description.getText());
            });
        });

        }
        
    @Override
    public void getLinkedPanel(Type type) {
        
        
    }

    @Override
    public JPanel getPanel() {
        // TODO Auto-generated method stub
        return panel;
    }
    
}
