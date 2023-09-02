package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.models.Seat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.Vector;

public class AdminDataTable extends JFrame implements ActionListener {
    private JTable tableA;
    private JTable tableB;
    private static final int cellWidth = 70;

    AdminDataTable(List<Seat> data, String[] headers, int roomNumber) {
        setSize(700, 700);

        DefaultTableModel modelA = new DefaultTableModel();
        modelA.setColumnIdentifiers(new String[]{"Student Id", "Seat Id"});

        DefaultTableModel modelB = new DefaultTableModel();
        modelB.setColumnIdentifiers(new String[]{"Student Id", "Seat Id"});

        for (Seat seat : data) {
            if (seat.getSeatId().startsWith("A")) {
                Vector<Object> row = new Vector<>();
                row.add(seat.getStudentId());
                row.add(seat.getSeatId());
                modelA.addRow(row);
            } else if (seat.getSeatId().startsWith("B")) {
                Vector<Object> row = new Vector<>();
                row.add(seat.getStudentId());
                row.add(seat.getSeatId());
                modelB.addRow(row);
            }
        }
        tableA = new JTable(modelA);
        tableB = new JTable(modelB);

        // Set row height for both tables
        int cellHeight = 30; // Adjust this value to fit your data
        setCellSize(tableA, cellWidth, cellHeight);
        setCellSize(tableB, cellWidth, cellHeight);

        JLabel labelA = new JLabel("<html><font size='10'>&emsp;A</font></html>");
        labelA.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelB = new JLabel("<html><font size='10'>&emsp;&emsp;B</font></html>");
        labelB.setHorizontalAlignment(JLabel.CENTER);

        // Create a top-level JPanel to control the layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Set padding to zero

        // Create separate JPanel for tables A and B
        JPanel panelA = new JPanel(new BorderLayout());
        JPanel panelB = new JPanel(new BorderLayout());

        // Add tables to their respective panels
        panelA.add(new JScrollPane(tableA), BorderLayout.CENTER);
        panelB.add(new JScrollPane(tableB), BorderLayout.CENTER);

        // Create a panel for labels and add labels to it
        JPanel labelPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing around the label

        Font titleFont = new Font("SansSerif", Font.BOLD, 24);

        JLabel titleLabel = new JLabel("Exam Seat Planning", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        labelPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        labelPanel.add(labelA, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        labelPanel.add(labelB, gbc);

        // Create a panel for tables and add table panels to it
        JPanel tablePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tablePanel.add(panelA);
        tablePanel.add(panelB);

        // Add label panel and table panel to the main panel
        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Create a panel for centering the main panel
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.add(mainPanel);

        // Add the centering panel to the dialog
        add(centeringPanel);
        addButton();

        setTitle("Seat Plan - Room " + roomNumber);
    }

    private void setCellSize(JTable table, int cellWidth, int cellHeight) {
        table.setRowHeight(cellHeight);
        TableColumnModel columnModel = table.getColumnModel();
        int totalWidth = 0; // Calculate the total width of all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setMinWidth(cellWidth);
            column.setMaxWidth(cellWidth);
            column.setPreferredWidth(cellWidth);
            totalWidth += cellWidth; // Add the cell width to the total width
        }

        // Set the preferred table size to match the total width
        Dimension tableSize = new Dimension(totalWidth, table.getPreferredSize().height);
        table.setPreferredScrollableViewportSize(tableSize);
    }



    private void addButton() {
        JButton printButton = new JButton("Print");
        printButton.addActionListener(this);
        add(printButton, BorderLayout.SOUTH);
    }

    void render() {
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Print")) {
            try {
                // Create a PrinterJob
                PrinterJob printerJob = PrinterJob.getPrinterJob();

                // Set the printable content to both tables
                printerJob.setPrintable(tableA.getPrintable(JTable.PrintMode.FIT_WIDTH, null, null));
                if (printerJob.printDialog()) {
                    printerJob.print();
                }

                printerJob.setPrintable(tableB.getPrintable(JTable.PrintMode.FIT_WIDTH, null, null));
                if (printerJob.printDialog()) {
                    printerJob.print();
                }
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
