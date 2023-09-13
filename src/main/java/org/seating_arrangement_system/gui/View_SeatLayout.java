package org.seating_arrangement_system.gui;

import org.seating_arrangement_system.db.models.Seat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class View_SeatLayout extends JFrame {
    private JTable tableA;
    private JTable tableB;
    private static final int cellWidth = 115;
    private List<Seat> data;
    private String[] headers;

    public View_SeatLayout(String title, List<Seat> data, String[] headers, int roomNumber) {
        this.data = data;
        this.headers = headers;
        setSize(750, 750);
        setTitle(title);

        // Organize data into two separate lists, one for A and one for B
        List<Seat> dataA = new Vector<>();
        List<Seat> dataB = new Vector<>();

        for (Seat seat : data) {
            if (seat.getSeatId().startsWith("A")) {
                dataA.add(seat);
            } else if (seat.getSeatId().startsWith("B")) {
                dataB.add(seat);
            }
        }
// Assuming data is a list of seats where each seat has a student ID and seat ID
// Create a map to store the mapping of seat IDs to student IDs
        Map<String, String> seatToStudentMap = new HashMap<>();
        for (Seat seat : data) {
            seatToStudentMap.put(seat.getSeatId(), String.valueOf(seat.getStudentId()));
        }

// Create seat layouts for A and B
        String[][] seatLayoutA = new String[5][2]; // 5 rows and 2 columns for section A
        String[][] seatLayoutB = new String[5][2]; // 5 rows and 2 columns for section B

// Populate the seatLayout arrays with student IDs based on seat IDs
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 2; col++) {
                String seatIdA = "A" + (row + 1) + (col == 0 ? "X" : "Y");
                String seatIdB = "B" + (row + 1) + (col == 0 ? "X" : "Y");

                String studentIdA = seatToStudentMap.get(seatIdA);
                String studentIdB = seatToStudentMap.get(seatIdB);

                seatLayoutA[row][col] = studentIdA != null ? studentIdA : "";
                seatLayoutB[row][col] = studentIdB != null ? studentIdB : "";
            }
        }

// Create table models for A and B
        DefaultTableModel modelA = new DefaultTableModel(seatLayoutA, new String[]{"AX", "AY"});
        DefaultTableModel modelB = new DefaultTableModel(seatLayoutB, new String[]{"BX", "BY"});

// Create tables with the models
        tableA = new JTable(modelA);
        tableB = new JTable(modelB);

        DefaultTableCellRenderer centerRenderer = new DataTable.CenterTableCellRenderer();
        tableA.setDefaultRenderer(Object.class, centerRenderer); // Object.class means it will apply to all columns
        tableB.setDefaultRenderer(Object.class, centerRenderer);

        // Set row height for both tables
        int cellHeight = 30; // Adjust this value to fit your data
        setCellSize(tableA, cellWidth, cellHeight);
        setCellSize(tableB, cellWidth, cellHeight);

        JLabel labelA = new JLabel("<html><font size='10'>&emsp;A</font></html>");
        labelA.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelB = new JLabel("<html><font size='10'>&emsp;&emsp;&emsp;&emsp;B</font></html>");
        labelB.setHorizontalAlignment(JLabel.CENTER);

        JLabel collegeLabel = new JLabel("KIST College of Information Technology", JLabel.CENTER);
        JLabel roomLabel = new JLabel("Room Number: " + roomNumber, JLabel.CENTER);
        Font collegeFont = new Font("SansSerif", Font.BOLD, 24);
        Font roomFont = new Font("SansSerif", Font.BOLD, 24);
        collegeLabel.setFont(collegeFont);
        roomLabel.setFont(roomFont);

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
        labelPanel.add(collegeLabel, gbc);

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(400, 2)); // Adjust the size as needed
        separator1.setForeground(Color.BLACK); // Set the color of the line

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(400, 2)); // Adjust the size as needed
        separator2.setForeground(Color.BLACK); // Set the color of the line

        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(400, 2)); // Adjust the size as needed
        separator3.setForeground(Color.BLACK); // Set the color of the line

        gbc.gridy = 1;
        labelPanel.add(collegeLabel, gbc);

        gbc.gridy =2;
        labelPanel.add(separator1, gbc);

        gbc.gridy = 3;
        labelPanel.add(roomLabel, gbc);

        gbc.gridy =4;
        labelPanel.add(separator2, gbc);

        gbc.gridy = 5;
        labelPanel.add(titleLabel, gbc);

        gbc.gridy =6;
        labelPanel.add(separator3, gbc);

        gbc.gridx = 0; // Reset the gridx
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Reset the gridwidth
        gbc.insets = new Insets(10, 50, 10, 10); // Add horizontal space around labelA
        labelPanel.add(labelA, gbc);

        gbc.gridx = 1; // Move to the next column for labelB
        gbc.insets = new Insets(10, 10, 10, 50); // Add horizontal space around labelB
        labelPanel.add(labelB, gbc);
        // Adjust the layout of tables
        tableA.setShowGrid(true);
        tableA.setGridColor(Color.BLACK);
        tableB.setShowGrid(true);
        tableB.setGridColor(Color.BLACK);

        // Add scroll panes to tables
        JScrollPane scrollPaneA = new JScrollPane(tableA);
        JScrollPane scrollPaneB = new JScrollPane(tableB);

        // Create a panel for tables and add table scroll panes to it
        JPanel tablePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tablePanel.add(scrollPaneA);
        tablePanel.add(scrollPaneB);

        // Add label panel and table panel to the main panel
        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Create a panel for centering the main panel
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.add(mainPanel);

        // Add the centering panel to the frame
        add(centeringPanel);

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
}
