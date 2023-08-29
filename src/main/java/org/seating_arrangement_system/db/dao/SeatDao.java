package org.seating_arrangement_system.db.dao;

import org.seating_arrangement_system.db.models.Seat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeatDao extends Dao {
    public class HallComboItem {
        private String id;
        private String name;

        public HallComboItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Hall(id=" + id + ", name=" + name + ")";
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public SeatDao() { super(); }

    public Object[][] getAll () {
        int rowSize = 0;
        Object[][] ret = null;
        try {
            String query = "select * from seatplan";
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = stmt.executeQuery(query);

            if (result.last()) {
                rowSize = result.getRow();
                result.first();
            }
            int colSize = 6;

            ret = new Object[rowSize][colSize];

            for (int i = 0; i < rowSize; i++) {
                for (int j = 0; j < colSize; j++) {
                    ret[i][j] = result.getString(j + 1);
                }
                result.next();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ret;
    }

    public Seat getSeat(int studentId) {
        Seat seat = null;
        String query = "select * from seatplan where student_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                seat = new Seat(
                        studentId,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        }
        catch(SQLException se) {
            se.printStackTrace();
        }
        return seat;
    }
    public List<HallComboItem> getAvailableHallIds() {
        List<HallComboItem> hallComboItems = new ArrayList<>();

        try {
            String query = "SELECT hall_id, name FROM hall";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("hall_id");
                String name = resultSet.getString("name");
                HallComboItem comboItem = new HallComboItem(id, name);
                hallComboItems.add(comboItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hallComboItems;
    }

    public List<Seat> getAllForHall(String hallInfo) {
        List<Seat> seatList = new ArrayList<>();
        String query = "SELECT * FROM seatplan where hall_info = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hallInfo);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Seat seat = new Seat(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("hall_info"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("seat_id"),
                        resultSet.getString("sem")
                );
                seatList.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

    public void insertSeat(Seat seat) {
        String query = "insert into seatplan(student_id, student_name, hall_info, room_no, seat_id, sem) values(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, seat.getStudentId());
            statement.setString(2, seat.getStudentName());
            statement.setString(3, seat.getHallInfo());
            statement.setInt(4, seat.getRoomNo());
            statement.setString(5, seat.getSeatId());
            statement.setString(6, seat.getSemester());
            int updated = statement.executeUpdate();
            System.out.println(updated);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void truncate() {
        String query = "truncate table seatplan";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Seat> getSeatsForHallAndRoom(String hallInfo, int roomNumber) {
        List<Seat> seatList = new ArrayList<>();
        String query = "SELECT * FROM seatplan WHERE hall_info = ? AND room_no = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hallInfo);
            statement.setInt(2, roomNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Seat seat = new Seat(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("hall_info"),
                        resultSet.getInt("room_no"),
                        resultSet.getString("seat_id"),
                        resultSet.getString("sem")
                );
                seatList.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

}
