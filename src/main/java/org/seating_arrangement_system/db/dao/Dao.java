package org.seating_arrangement_system.db.dao;

import java.sql.*;

public abstract class Dao {
    protected static Connection connection;

    //Class.forName("com.mysql.cj.jdbc.Driver");
    private final static String url = "jdbc:mysql://localhost/exam_seating_arrangement";
    private final static String dbUser = "root";
    private final static String dbPassword = "kist@123";

    Dao() {
        try {
            connection = DriverManager.getConnection(url,dbUser,dbPassword);
        }
        catch(SQLException se) {
            System.out.println("db connection failed!");
            se.printStackTrace();
        }
    }
    public void destroy() {
        try {
            connection.close();
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
    }
}
