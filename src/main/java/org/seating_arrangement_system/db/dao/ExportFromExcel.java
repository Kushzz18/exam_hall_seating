package org.seating_arrangement_system.db.dao;

import org.seating_arrangement_system.gui.AdminView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportFromExcel extends Dao {
    private boolean exportCanceled = false;
    public ExportFromExcel() {
        int batchSize = 20;

        // Connection connection = null;

        try {

            connection.setAutoCommit(false);
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//            connection.setAutoCommit(false);

            String sql = "INSERT INTO student (id,name,sem) Values(?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

                String lineText = null;
                int count = 0;

                lineReader.readLine();
                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(",");

                    if (data.length >= 1) {
                        String id = data[0];
                        String name = data[1];
                        String sem = data[2];


                        statement.setInt(1, Integer.parseInt(id));
                        statement.setString(2, name);
                        statement.setString(3, sem);
                        statement.addBatch();

                        count++;

                        if (count % batchSize == 0) {
                            statement.executeBatch();
                        }
                        // Continue processing the data
                    } else {
                        // Handle the case where data is empty or insufficient
                    }

                }
                lineReader.close();
                statement.executeBatch();
                connection.commit();
                connection.close();
                System.out.println("Data has been inserted successfully.");

                AdminView adminView = new AdminView();
                adminView.setVisible(true);


            }else{
                exportCanceled = true;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            // Step 3: After export, check the flag and take appropriate action
            if (exportCanceled) {
                // The export was canceled, take the user back to the admin view
                new AdminView();
            }
        }

    }
}

