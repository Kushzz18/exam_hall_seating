
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

public class DialogBoxRoom extends CenteredLayout {

    private SeatDao.HallComboItem selectedHall;

    public DialogBoxRoom(SeatDao.HallComboItem selectedHall) {
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

//        for (int i = 1; i <= 4; i++) {
//            JButton roomButton = createButton("Room " + i);
//            buttonPanel.add(roomButton);
//        }

        for (Room room : rooms) {
            JButton roomButton = createButton("Room " + room.getRoomNumber());
            buttonPanel.add(roomButton);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String label) {
        int roomNumber = Integer.parseInt(label.substring("Room ".length()));
        JButton button = new JButton(label);
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
            DataTable dataTable = new DataTable(seatList, headers);
            dataTable.render();
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
