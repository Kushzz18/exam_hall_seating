package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.models.Hall;
import org.seating_arrangement_system.db.models.Room;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteRoom extends FormLayout {
    public DeleteRoom() {
        setSize(400, 500);
        topLabel.setText("Select room");

        HallDao hallDao = new HallDao();
        List<Hall> hallList = hallDao.getAll();

        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        JList<String> roomList = new JList<>(roomListModel);

        JComboBox<Hall> hallSelect = new JComboBox<>(hallList.toArray(new Hall[0]));
        hallSelect.setSelectedIndex(0);
        compList.add(hallSelect);

        JLabel roomNoLabel = new JLabel("Enter Room Number:");
        JTextField roomNoField = new JTextField(15);
        compList.add(makeGroup(roomNoLabel, roomNoField));

        RoundedPanel btnPane = new RoundedPanel(20);

        RoundedButton delete = new RoundedButton("Delete",20);

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
                    int remainingRooms = roomDao.getRoomCount(hall.getId());
                    JOptionPane.showMessageDialog(null, "Room deleted successfully!\nRemaining rooms: " + remainingRooms);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid room number! Must be an integer.");
            }
        });

        RoundedButton back = new RoundedButton("Back",20);

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
}
