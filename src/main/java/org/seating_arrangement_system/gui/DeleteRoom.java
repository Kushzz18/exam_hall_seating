package org.seating_arrangement_system.gui;
import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.models.Hall;
import org.seating_arrangement_system.db.models.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteRoom extends FormLayout {
    private JComboBox<Hall> hallSelect;
    private JComboBox<Room> roomSelect;

    private JComboBox<Integer> roomNumbersSelect;
    private List<Integer> availableRoomNumbers;

    public DeleteRoom() {
        setSize(400, 500);
        topLabel.setText("Select room");

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
        roomNumbersSelect = new JComboBox<>();
        compList.add(makeGroup(roomNoLabel, roomNumbersSelect));

        // Add an ActionListener to hallSelect to dynamically update room numbers
        hallSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomSelect();
            }
        });

        // Initialize the room numbers for the first hall
        updateRoomSelect();

        JLabel roomNoLabel2 = new JLabel("Enter Room Number:");
        JTextField roomNoField = new JTextField(15);
        compList.add(makeGroup(roomNoLabel2, roomNoField));

        RoundedPanel btnPane = new RoundedPanel(20);

        RoundedButton delete = new RoundedButton("Delete", 20);

        RoomDao roomDao = new RoomDao();

        delete.addActionListener(action -> {
            try {
                int roomNumber = Integer.parseInt(roomNoField.getText());
                Hall hall = (Hall) hallSelect.getSelectedItem();
                assert hall != null;

                int deleted = roomDao.delete(hall.getId(), roomNumber);
                if (deleted == 0) {
                    JOptionPane.showMessageDialog(null, "Room Number does not exist!");
                } else {
                    // Get the updated list of rooms for the selected hall
                    List<Room> updatedRoomList = roomDao.getAllRoomsInHall(hall.getId());

                    int remainingRooms = updatedRoomList.size(); // Use the size of the updated list
                    JOptionPane.showMessageDialog(null, "Room deleted successfully!\nRemaining rooms: " + remainingRooms);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid room number! Must be an integer.");
            }
        });

        RoundedButton back = new RoundedButton("Back", 20);

        back.addActionListener(action -> {
            this.dispose();
            new AdminView();
        });

        btnPane.add(delete);
        btnPane.add(back);

        compList.add(btnPane);
        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    // Helper method to update the roomNumbersSelect JComboBox based on the selected hall
    private void updateRoomSelect() {
        Hall selectedHall = (Hall) hallSelect.getSelectedItem();
        if (selectedHall != null) {
            RoomDao roomDao = new RoomDao();
            availableRoomNumbers = roomDao.getAvailableRoomNumbers(selectedHall.getId());

            DefaultComboBoxModel<Integer> roomComboBoxModel = new DefaultComboBoxModel<>(availableRoomNumbers.toArray(new Integer[0]));
            roomNumbersSelect.setModel(roomComboBoxModel);
        }
    }

    public JPanel makeGroup(JLabel label, JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(component);
        panel.setOpaque(false);
        return panel;
    }
}
