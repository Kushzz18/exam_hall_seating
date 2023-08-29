package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.models.Seat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.Vector;

public class DataTable extends JFrame {
    private JTable table;
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

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
//        addButton();
    }
//    private void addButton() {
//        JButton printButton = new JButton("Print");
//        printButton.addActionListener(this); // Register the button with the ActionListener
//        add(printButton, BorderLayout.SOUTH); // Add the button to the bottom of the frame
//    }

    void render() {
        setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // Handle the button click event here
//        if (e.getActionCommand().equals("Print")) {
//            try {
//                // Create a PrinterJob
//                PrinterJob printerJob = PrinterJob.getPrinterJob();
//
//                // Set the printable content to the JTable
//                printerJob.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH, null, null));
//
//                // Show the print dialog to the user
//                if (printerJob.printDialog()) {
//                    // If the user confirms, start the printing process
//                    printerJob.print();
//                }
//            } catch (PrinterException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}
