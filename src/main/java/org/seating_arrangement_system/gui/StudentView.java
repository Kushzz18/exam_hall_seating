package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.models.Seat;
import org.seating_arrangement_system.db.dao.SeatDao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentView extends CenteredLayout {
    private JComboBox<SeatDao.HallComboItem> hallComboBox;
    private SeatDao.HallComboItem selectedHall;
    private String selectedRoom;

    public StudentView() {
        this.setTitle("Student View");

        JButton viewAll = new JButton("View All seatplan"); // 10 is the radius
        viewAll.setPreferredSize(new Dimension(150, 50));

        JButton viewSingle = new JButton("View single seatplan");
        viewSingle.setPreferredSize(new Dimension(150, 50));

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 40));


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
                new DialogBoxRoom(selectedHall);
//                SeatDao.HallComboItem selectedHall = (SeatDao.HallComboItem) hallComboBox.getSelectedItem();
//                System.out.println("Inside Hall : " + selectedHall.getName());
//                List<Seat> seatList = seatDao.getAllForHall(selectedHall.getName());
//
//                String[] headers = {"Student Id", "Student Name", "Hall Info", "Room No", "Seat Id", "Semester"};
//                DataTable dataTable = new DataTable(seatList, headers);
//                dataTable.render();
            }
        });



//        viewAll.addActionListener(action ->{
//
//            this.dispose();
//            new DialogBoxRoom();
//        });

        viewSingle.addActionListener(action -> {
            this.dispose();
            new SingleSeatPlan();
        });

        //   JButton back = new JButton("Back");

        back.addActionListener(action -> {
            this.dispose();
            new Welcome();
        });

        compList.add(viewAll);
        compList.add(viewSingle);
        compList.add(back);

        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
