package org.seating_arrangement_system.db.dao;

import org.seating_arrangement_system.db.models.Hall;
import org.seating_arrangement_system.db.models.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends Dao {

    public RoomDao() {
        super();
    }

    public List<Room> getAll(int hall_id) {
        List<Room> rooms = new ArrayList<Room>();
        String query = "select * from room where hall_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hall_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                        );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public boolean alreadyExists(int hallId, int roomNo) {
        boolean exists = false;
        String query = "select * from room where hall_id = ? and room_no = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            statement.setInt(2, roomNo);
             ResultSet resultSet = statement.executeQuery();
             exists = resultSet.next();
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return exists;
    }

    public void add(int hallId, int roomNo, int col, int benchNumber) {
        String query = "insert into room(hall_id, room_no, col, bench_per_col) values(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            statement.setInt(2, roomNo);
            statement.setInt(3, col);
            statement.setInt(4, benchNumber);
            statement.executeUpdate();
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public int delete(int hallId, int roomNo) {
        int affected = 0;
        String query = "delete from room where hall_id = ? and room_no = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            statement.setInt(2, roomNo);
            affected = statement.executeUpdate();
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return affected;
    }
    public boolean areAllRoomsDeleted(int hallId) {
        boolean allRoomsDeleted = true;
        String query = "select * from room where hall_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            allRoomsDeleted = !resultSet.next(); // If resultSet is not empty, rooms exist
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return allRoomsDeleted;
    }
    public int getRoomCount(int hallId) {
        int roomCount = 0;
        String query = "select count(*) from room where hall_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roomCount = resultSet.getInt(1);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return roomCount;
    }

    public List<Integer> getAvailableRoomNumbers(int hallId) {
        List<Integer> availableRoomNumbers = new ArrayList<>();
        String query = "SELECT room_no FROM room WHERE hall_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                availableRoomNumbers.add(resultSet.getInt("room_no"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return availableRoomNumbers;
    }

    public List<Room> getAllRoomsInHall(int hallId) {
        List<Room> rooms = new ArrayList<Room>();
        String query = "select * from room where hall_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                );
                rooms.add(room);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;

    }


}

