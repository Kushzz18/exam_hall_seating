package org.seating_arrangement_system.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DialogBoxRoom extends CenteredLayout {

    public DialogBoxRoom() {
        setTitle("Divided Window with Centered Buttons");
        setSize(600, 600);

        JButton room1 = createButton("Room 1");
        JButton room2 = createButton("Room 2");
        JButton room3 = createButton("Room 3");
        JButton room4 = createButton("Room 4");

        // Add buttons directly to the content pane
//        getContentPane().setLayout(new GridLayout(2, 2));
//        getContentPane().add(room1);
//        getContentPane().add(room2);
//        getContentPane().add(room3);
//        getContentPane().add(room4);


//        setLayout(new GridLayout(2, 2));


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(50, 50, 50, 50);
        constraints.anchor = GridBagConstraints.CENTER;
        add(room1, constraints);

        constraints.gridx = 1;
        add(room2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(room3, constraints);

        constraints.gridx = 1;
        add(room4, constraints);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(150, 50));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        getContentPane(),  // Use getContentPane() for the dialog box
                        "You clicked " + label,
                        "Button Clicked",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        return button;
    }
}
