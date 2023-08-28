package org.seating_arrangement_system.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomLayout extends CenteredLayout {
    private JPanel roomPanel;

    public RoomLayout() {
        setTitle("Room Layout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(5, 5)); // Customize rows and columns

        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                JButton seatButton = createSeatButton("Seat " + row + "-" + col);
                roomPanel.add(seatButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(roomPanel);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createSeatButton(String seatName) {
        JButton seatButton = new JButton(seatName);
        seatButton.setPreferredSize(new Dimension(70, 50));

        seatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RoomLayout.this, "Clicked: " + seatName);
            }
        });

        return seatButton;
    }
}
