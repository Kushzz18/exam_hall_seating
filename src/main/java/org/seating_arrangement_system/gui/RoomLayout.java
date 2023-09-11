package org.seating_arrangement_system.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomLayout extends CenteredLayout {
    private RoundedPanel roomPanel;

    public RoomLayout() {
        setTitle("Room Layout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        roomPanel = new RoundedPanel(20);
        roomPanel.setLayout(new GridLayout(5, 5)); // Customize rows and columns

        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                RoundedButton seatButton = createSeatButton("Seat " + row + "-" + col);
                roomPanel.add(seatButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(roomPanel);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private RoundedButton createSeatButton(String seatName) {
        RoundedButton seatButton = new RoundedButton(seatName,20);
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
