package org.seating_arrangement_system.db.dao;

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

    public ExportFromExcel() {
        int batchSize = 20;

        // Connection connection = null;

        try {
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

                    String id = data[0];
                    String name = data[1];
                    String address = data[2];
                    String salary = data[3];

                    statement.setInt(1, Integer.parseInt(id));
                    statement.setString(2, name);
                    statement.setString(3, address);
                    statement.setInt(4, Integer.parseInt(salary));
                    statement.addBatch();
                    if (count % batchSize == 0) {
                        statement.executeBatch();
                    }
                }
                lineReader.close();
                statement.executeBatch();
                connection.commit();
                connection.close();
                System.out.println("Data has been inserted successfully.");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

