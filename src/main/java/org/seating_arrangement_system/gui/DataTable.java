package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.models.Seat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class DataTable extends JFrame {

    DataTable(List<Seat> data, String[] headers) {

        setSize(700, 300);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);

        for (Seat seat : data) {
            Vector<Object> row = new Vector<>();
            row.add(seat.getStudentId());
            row.add(seat.getStudentName());
            row.add(seat.getHallInfo());
            row.add(seat.getRoomNo());
            row.add(seat.getSeatId());
            row.add(seat.getSemester());
            model.addRow(row);
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }

    void render() {
        setVisible(true);
    }
}
