package org.seating_arrangement_system.gui;
//
//import org.seating_arrangement_system.db.dao.HallDao;
//import org.seating_arrangement_system.db.dao.RoomDao;
//import org.seating_arrangement_system.db.models.Hall;
//
//import javax.swing.*;
//import java.util.List;
//
//public class AddRoom extends FormLayout {
//    public AddRoom() {
//        setSize(400, 400);
//
//        topLabel.setText("Select hall");
//
//        HallDao hallDao = new HallDao();
//        List<Hall> hallList = hallDao.getAll();
//
//        JComboBox hallSelect = new JComboBox(hallList.toArray());
//        hallSelect.setSelectedIndex(0);
//        compList.add(hallSelect);
//
//        JLabel roomNoLabel = new JLabel("Room Number:");
//        JTextField roomNumberField = new JTextField(15);
//
//        compList.add(makeGroup(roomNoLabel, roomNumberField));
//
//        JLabel colLabel = new JLabel("Column Number:");
//        JTextField colField = new JTextField(15);
//
//        compList.add(makeGroup(colLabel, colField));
//
//
//        JLabel benchNumberLabel = new JLabel("Bench per column:");
//        JTextField benchPerColumn = new JTextField(15);
//
//        compList.add(makeGroup(benchNumberLabel, benchPerColumn));
//
//        RoundedPanel btnPane = new RoundedPanel(20);
//
//        RoundedButton addRoomButton = new RoundedButton("Add Room",20);
//
//        addRoomButton.addActionListener(action -> {
//            Hall hall = (Hall)hallSelect.getSelectedItem();
//            assert hall != null;
//            try {
//                int roomNo = Integer.parseInt(roomNumberField.getText());
//                int columnNumber = Integer.parseInt(colField.getText());
//                int brenchNumber = Integer.parseInt(benchPerColumn.getText());
//                RoomDao roomDao = new RoomDao();
//                if (roomDao.alreadyExists(hall.getId(), roomNo)) {
//                    JOptionPane.showMessageDialog(null, "Room already exists");
//                } else {
//                    roomDao.add(hall.getId(), roomNo, columnNumber, brenchNumber);
//                    JOptionPane.showMessageDialog(null, "Room added!");
//                }
//            }
//            catch(Exception e) {
//                JOptionPane.showMessageDialog(null, "Input is invalid! all input must be integer");
//            }
//
//        });
//
//        RoundedButton back = new RoundedButton("Back",20);
//
//        back.addActionListener(action -> {
//            this.dispose();
//            new AdminView();
//        });
//
//        btnPane.add(addRoomButton);
//        btnPane.add(back);
//
//        compList.add(btnPane);
//        render();
//        setVisible(true);
//        setLocationRelativeTo(null);
//    }
//}


import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.models.Hall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddRoom extends FormLayout {

    private JComboBox<Integer> roomNumberSelect;
    private JComboBox<Hall> hallSelect;
    public List<Integer> availableRoomNumbers;





    public AddRoom() {
        setSize(400, 400);

        topLabel.setText("Select hall");

        HallDao hallDao = new HallDao();
        List<Hall> hallList = hallDao.getAll();

        hallSelect = new JComboBox<>(hallList.toArray(new Hall[0]));
        hallSelect.setSelectedIndex(0);
        compList.add(hallSelect);
        Hall selectedHall = (Hall) hallSelect.getSelectedItem();
        if (selectedHall != null) {
            RoomDao roomDao = new RoomDao();
            availableRoomNumbers = roomDao.getAvailableRoomNumbers(selectedHall.getId());
        }

        JLabel roomNoLabel = new JLabel("Room Number:");
        roomNumberSelect = new JComboBox<>();
        compList.add(makeGroup(roomNoLabel, roomNumberSelect));

        // Add an ActionListener to hallSelect to dynamically update room numbers
        hallSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomNumbers();
            }
        });

        // Initialize the room numbers for the first hall
        updateRoomNumbers();

        JLabel roomNoLabel2 = new JLabel("Add Room Number:");
        JTextField roomNumberField2 = new JTextField(15);
        compList.add(makeGroup(roomNoLabel2, roomNumberField2));

        JLabel colLabel = new JLabel("Column Number:");
        JTextField colField = new JTextField(15);
        compList.add(makeGroup(colLabel, colField));

        JLabel benchNumberLabel = new JLabel("Bench per column:");
        JTextField benchPerColumn = new JTextField(15);
        compList.add(makeGroup(benchNumberLabel, benchPerColumn));

        RoundedPanel btnPane = new RoundedPanel(20);

        RoundedButton addRoomButton = new RoundedButton("Add Room", 20);

        addRoomButton.addActionListener(action -> {
            Hall hall = (Hall) hallSelect.getSelectedItem();
            assert hall != null;
            try {
                int roomNo = Integer.parseInt(roomNumberField2.getText());
                int columnNumber = Integer.parseInt(colField.getText());
                int benchNumber = Integer.parseInt(benchPerColumn.getText());
                RoomDao roomDao = new RoomDao();
                // Implement the getAvailableRoomNumbers method in RoomDao
                // List<Integer> availableRoomNumbers = roomDao.getAvailableRoomNumbers(hall.getId());
                // Check if roomNo is in availableRoomNumbers
                if (roomDao.alreadyExists(hall.getId(), roomNo)) {
                    JOptionPane.showMessageDialog(null, "Room already exists");
                }else {
                    roomDao.add(hall.getId(), roomNo, columnNumber, benchNumber);
                    JOptionPane.showMessageDialog(null, "Room added!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Input is invalid! All input must be integers.");
            }
        });

        RoundedButton back = new RoundedButton("Back", 20);

        back.addActionListener(action -> {
            this.dispose();
            new AdminView();
        });

        btnPane.add(addRoomButton);
        btnPane.add(back);

        compList.add(btnPane);
        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    // Helper method to update available room numbers based on the selected hall
    private void updateRoomNumbers() {
        Hall selectedHall = (Hall) hallSelect.getSelectedItem();
        if (selectedHall != null) {
            RoomDao roomDao = new RoomDao();
            availableRoomNumbers = roomDao.getAvailableRoomNumbers(selectedHall.getId());

            // Implement the getAvailableRoomNumbers method in RoomDao
            // List<Integer> availableRoomNumbers = roomDao.getAvailableRoomNumbers(selectedHall.getId());

            // Clear and update the roomNumberSelect JComboBox
            roomNumberSelect.removeAllItems();
            for (Integer roomNumber : availableRoomNumbers) {
                roomNumberSelect.addItem(roomNumber);
            }
        }
    }

    public JPanel makeGroup(JComponent... components) {
      //  JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JComponent component : components) {
            panel.add(component);
        }
        panel.setOpaque(false);
        return panel;
    }

}

