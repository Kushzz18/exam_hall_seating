package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.dao.SeatDao;
import org.seating_arrangement_system.db.models.Seat;
import org.seating_arrangement_system.seat.plan.SeatPlanner;
import org.seating_arrangement_system.util.SeatSizeValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminView extends CenteredLayout {

    private JComboBox<SeatDao.HallComboItem> hallComboBox;
    private SeatDao.HallComboItem selectedHall;


    AdminView () {
        topLabel.setText("Admin Section");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 25));
        topLabel.setForeground(Color.black);

        topInsets = new Insets(40, 0, 0, 0);

        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addHall = new JButton("Add Hall");

        addHall.addActionListener(action -> {
            this.dispose();
            new AddHall();
        });

        
        compList.add(addHall);

        JButton addRoom = new JButton("Add Room");

        addRoom.addActionListener(action -> {
            this.dispose();
            new AddRoom();
        });
        
        compList.add(addRoom);

        JButton delHall = new JButton("Delete Hall");

        delHall.addActionListener(action -> {

        });

        
        compList.add(delHall);

        JButton delRoom = new JButton("Delete Room");

        delRoom.addActionListener(action -> {
            this.dispose();
            new DeleteRoom();
        });

        delHall.addActionListener(action -> {
            this.dispose();
            new DeleteHall();
        });


        compList.add(delRoom);

        JButton viewAll = new JButton("View All seatplan");

        viewAll.addActionListener(action -> {
            SeatDao seatDao = new SeatDao();
            List<SeatDao.HallComboItem> hallComboItems = seatDao.getAvailableHallIds();
            hallComboBox = new JComboBox<>();
            for (SeatDao.HallComboItem comboItem : hallComboItems) {
                hallComboBox.addItem(comboItem);
            }
            int option = JOptionPane.showConfirmDialog(
                    this,
                    hallComboBox,
                    "Select Hall",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {

                SeatDao.HallComboItem selectedHall = (SeatDao.HallComboItem) hallComboBox.getSelectedItem();
                this.dispose();
                new AdminRoomLayout(selectedHall);
//                SeatDao.HallComboItem selectedHall = (SeatDao.HallComboItem) hallComboBox.getSelectedItem();
//                System.out.println("Inside Hall : " + selectedHall.getName());
//                List<Seat> seatList = seatDao.getAllForHall(selectedHall.getName());
//
//                String[] headers = {"Student Id", "Student Name", "Hall Info", "Room No", "Seat Id", "Semester"};
//                DataTable dataTable = new DataTable(seatList, headers);
//                dataTable.render();
            }
        });

        compList.add(viewAll);



        JButton logout = new JButton("Logout");

        logout.addActionListener(action -> {
            this.dispose();
            new Welcome();
        });


        JButton generate = new JButton("Generate Seatplan");

        generate.addActionListener(action -> {
            SeatSizeValidator sizeValidator = new SeatSizeValidator();
            if (!sizeValidator.isValid()) {
                JOptionPane.showMessageDialog(null, "Not enough seat! Please add hall and room");
            } else {
                SeatPlanner seatPlanner = new SeatPlanner();
                seatPlanner.truncate();
                seatPlanner.plan();
                JOptionPane.showMessageDialog(null, "Seatplan successfull!");
            }
        });

        compList.add(generate);
        compList.add(logout);

        render();
        setVisible(true);
    }
}