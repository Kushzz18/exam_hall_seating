package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.models.Hall;

import javax.swing.*;
import java.util.List;

public class DeleteHall extends FormLayout {
    public DeleteHall() {
        setSize(400, 300);

        topLabel.setText("Select hall");

        HallDao hallDao = new HallDao();
        RoomDao roomDao = new RoomDao();

        List<Hall> hallList = hallDao.getAll();

        JComboBox<Hall> hallSelect = new JComboBox<>(hallList.toArray(new Hall[0]));
        hallSelect.setSelectedIndex(0);
        compList.add(hallSelect);

        RoundedPanel btnPane = new RoundedPanel(20);

        RoundedButton delete = new RoundedButton("Delete",20);

        delete.addActionListener(action -> {
            Hall hall = (Hall) hallSelect.getSelectedItem();
            assert hall != null;

            if (roomDao.areAllRoomsDeleted(hall.getId())) {
                hallDao.delete(hall);
                JOptionPane.showMessageDialog(null, "Deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "All the rooms must be deleted before deleting the Hall");
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
