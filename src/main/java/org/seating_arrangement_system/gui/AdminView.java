package org.seating_arrangement_system.gui;
import org.seating_arrangement_system.db.dao.ExportFromExcel;

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


    public AdminView() {
        topLabel.setText("Admin Section");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 25));
        topLabel.setForeground(Color.black);

        topInsets = new Insets(40, 0, 0, 0);

        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        RoundedButton addHall = new RoundedButton("Add Hall",20);

        addHall.addActionListener(action -> {
            this.dispose();
            new AddHall();
        });


        compList.add(addHall);

        RoundedButton addRoom = new RoundedButton("Add Room",20);

        addRoom.addActionListener(action -> {
            this.dispose();
            new AddRoom();
        });

        compList.add(addRoom);

        RoundedButton delHall = new RoundedButton("Delete Hall",20);

        delHall.addActionListener(action -> {

        });


        compList.add(delHall);

        RoundedButton delRoom = new RoundedButton("Delete Room",20);

        delRoom.addActionListener(action -> {
            this.dispose();
            new DeleteRoom();
        });

        delHall.addActionListener(action -> {
            this.dispose();
            new DeleteHall();
        });


        compList.add(delRoom);

        RoundedButton viewAll = new RoundedButton("View All seatplan",20);

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



        RoundedButton logout = new RoundedButton("Logout",20);

        logout.addActionListener(action -> {
            this.dispose();
            new Welcome();
        });


        RoundedButton generate = new RoundedButton("Generate Seatplan",20);

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



        RoundedButton export = new RoundedButton("Export from excel",20);

        export.addActionListener(action -> {
            this.dispose();
            new ExportFromExcel();
        });

        compList.add(generate);
        compList.add(export);
        compList.add(logout);

        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}