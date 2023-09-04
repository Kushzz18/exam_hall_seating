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
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.print.*;
import javax.swing.JSeparator;


public class AdminDataTable extends JFrame implements ActionListener {
    private JTable tableA;
    private JTable tableB;
    private static final int cellWidth = 115;

    AdminDataTable(List<Seat> data, String[] headers, int roomNumber) {
        setSize(700, 800);

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

        DefaultTableCellRenderer centerRenderer = new CenterTableCellRenderer();
        tableA.setDefaultRenderer(Object.class, centerRenderer); // Object.class means it will apply to all columns
        tableB.setDefaultRenderer(Object.class, centerRenderer);

        // Set row height for both tables
        int cellHeight = 30; // Adjust this value to fit your data
        setCellSize(tableA, cellWidth, cellHeight);
        setCellSize(tableB, cellWidth, cellHeight);

        JLabel labelA = new JLabel("<html><font size='10'>A</font></html>");
        labelA.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelB = new JLabel("<html><font size='10'>&emsp;&emsp;&emsp;B</font></html>");
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

        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        JLabel titleLabel = new JLabel("Exam Seat Planning", JLabel.CENTER);

        titleLabel.setFont(titleFont);
        labelPanel.add(titleLabel, gbc);
        labelPanel.add(collegeLabel, gbc);

    titleLabel.setFont(titleFont);
       // labelPanel.add(titleLabel, gbc);


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
    public class CenterTableCellRenderer extends DefaultTableCellRenderer {
        public CenterTableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return component;
        }
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

                // Set the printable content to the entire frame
                printerJob.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
                        if (page > 0) {
                            return NO_SUCH_PAGE;
                        }

                        // Calculate the scale factor to fit the frame within the page
                        double scaleFactor = pf.getImageableWidth() / getWidth();
                        double scaleYFactor = pf.getImageableHeight() / getHeight();
                        double scale = Math.min(scaleFactor, scaleYFactor);

                        // Apply the scale transformation to the graphics object
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.translate(pf.getImageableX(), pf.getImageableY());
                        g2d.scale(scale, scale);

                        // Paint the frame onto the page, excluding the button
                        printAll(g2d);

                        return PAGE_EXISTS;
                    }
                });

                if (printerJob.printDialog()) {
                    // Remove the "Print" button temporarily
                    remove(getContentPane().getComponent(1));
                    validate();

                    printerJob.print();

                    // Add the "Print" button back after printing
                    addButton();
                    validate();
                }
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }



}
