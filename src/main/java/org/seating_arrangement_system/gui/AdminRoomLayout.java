package org.seating_arrangement_system.gui;


import org.seating_arrangement_system.db.dao.SeatDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.seating_arrangement_system.db.models.Seat;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.models.Room;
import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.models.Hall;

public class AdminRoomLayout extends CenteredLayout {

    private SeatDao.HallComboItem selectedHall;

    public AdminRoomLayout(SeatDao.HallComboItem selectedHall) {
        this.selectedHall = selectedHall;
        setTitle("Room Layout");
        setSize(600, 600);

        JComboBox<String> roomComboBox = new JComboBox<>();
        RoomDao roomDao = new RoomDao();

        int hallId = getHallId(selectedHall.getName());
        java.util.List<Room> rooms = roomDao.getAll(hallId);
        for (Room room : rooms) {
            roomComboBox.addItem("Room " + room.getRoomNumber());
        }

        // Create buttons for each room
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        add(buttonPanel, BorderLayout.CENTER);

        RoundedPanel roundedButtonPanel = new RoundedPanel(20);
        roundedButtonPanel.setLayout(new BorderLayout());
        roundedButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        add(roundedButtonPanel, BorderLayout.CENTER);


        for (Room room : rooms) {
            JButton roomButton = createButton("Room " + room.getRoomNumber());
            buttonPanel.add(roomButton);
        }


        RoundedButton backButton = new RoundedButton("Back",20);
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(new BackButtonListener());
        add(backButton, BorderLayout.SOUTH);

        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //buttonPanel2.setBackground(new Color(95, 111, 146));
        buttonPanel2.add(backButton);
        this.add(buttonPanel2, BorderLayout.SOUTH);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    private JButton createButton(String label) {
        int roomNumber = Integer.parseInt(label.substring("Room ".length()));
        RoundedButton button = new RoundedButton(label,20);
        button.setPreferredSize(new Dimension(150, 50));

        button.addActionListener(new RoomButtonListener(roomNumber));

        return button;
    }

    private class RoomButtonListener implements ActionListener {
        private int roomNumber;
        public RoomButtonListener(int roomNumber) {

            this.roomNumber = roomNumber;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            SeatDao seatDao = new SeatDao();
            java.util.List<Seat> seatList = seatDao.getSeatsForHallAndRoom(selectedHall.getName(), roomNumber);

            System.out.println("Selected Hall: " + selectedHall.getName());
            System.out.println("Selected Room Number: " + roomNumber);

            String[] headers = {"Student Id", "Student Name", "Hall Info", "Room No", "Seat Id", "Semester"};
            AdminDataTable dataTable = new AdminDataTable(seatList, headers,roomNumber);
            dataTable.render();
        }
    }
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           dispose();
            new AdminView();
        }
    }

    private int getHallId(String hallName) {
        HallDao hallDao = new HallDao();
        java.util.List<Hall> halls = hallDao.getAll();

        for (Hall hall : halls) {
            if (hall.getName().equals(hallName)) {
                return hall.getId();
            }
        }
        return -1; // Return a value that indicates hall not found
    }


}

