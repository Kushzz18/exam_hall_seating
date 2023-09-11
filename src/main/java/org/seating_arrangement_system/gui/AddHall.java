package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.dao.HallDao;

import javax.swing.*;

public class AddHall extends FormLayout {
    AddHall() {

        setSize(400, 500);

        topLabel.setText("Add Hall");

        JLabel enterHallId = new JLabel("Enter hall id:");
        JTextField hallIdField = new JTextField(15);
        compList.add(makeGroup(enterHallId, hallIdField));

        JLabel enterHallName = new JLabel("Enter Hall Name:");
        JTextField hallNameField = new JTextField(15);

        compList.add(makeGroup(enterHallName, hallNameField));

        RoundedPanel btnPane = new RoundedPanel(20);
        RoundedButton create = new RoundedButton("Create",20);


        create.addActionListener(action -> {
            HallDao hallDao = new HallDao();
            try {
                int hallId = Integer.parseInt(hallIdField.getText());
                String hallName = hallNameField.getText();
                if (hallDao.idExists(hallId)) {
                    JOptionPane.showMessageDialog(null, "id already exists");
                }
                else if (hallDao.nameExists(hallName)) {
                    JOptionPane.showMessageDialog(null, "Hall name already exists");
                }
                else {
                    hallDao.insertHall(hallId, hallName);
                    JOptionPane.showMessageDialog(null, "Successfully added hall!");
                }
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "id must be an integer");
            }
        });

        RoundedButton back = new RoundedButton("Back",20);

        back.addActionListener(action -> {
            this.dispose();
            new AdminView();
        });

        btnPane.add(create);
        btnPane.add(back);

        compList.add(btnPane);
        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
