package org.seating_arrangement_system.gui;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import org.seating_arrangement_system.db.dao.SeatDao;
import org.seating_arrangement_system.db.models.Seat;

import javax.swing.*;
import java.awt.*;

public class SingleSeatPlan extends FormLayout {
    SingleSeatPlan() {
        setSize(400, 300);
        topLabel.setText("Enter your ID");
        topLabel.setFont(new Font("SF Pro", Font.PLAIN, 18));
        topLabel.setForeground(Color.black);
        JLabel enterName = new JLabel("Student ID: ");
        JTextField idField = new JTextField(15);
        compList.add(makeGroup(enterName, idField));

        RoundedPanel btnPane = new RoundedPanel(10);
        RoundedButton search = new RoundedButton("Search",20);

        search.addActionListener(action -> {
            SeatDao seatDao = new SeatDao();
            try {
                int id = Integer.parseInt(idField.getText());
                Seat seat = seatDao.getSeat(id);
                if (seat == null) {
                    JOptionPane.showMessageDialog(null, "Invalid id or seatplan is not made yet");
                }
                else {

                   String message = "Seat Information:\n" +
                             "Seat ID: " + seat.getStudentId() + "\n" +
                             "Student Name: " + seat.getStudentName() + "\n" +
                             "Hall Info: " + seat.getHallInfo() + "\n" +
                             "Room No: " + seat.getRoomNo() + "\n" +
                             "Seat ID: " + seat.getSeatId();
            JOptionPane.showMessageDialog(null, message);
                  //  JOptionPane.showMessageDialog(null, seat.toString());
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Not a valid integer id");
            }
        });

        RoundedButton back = new RoundedButton("Back",20);

        back.addActionListener(action -> {
            this.dispose();
            new StudentView();
        });


        btnPane.add(search);
        btnPane.add(back);

        compList.add(btnPane);
        render();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
