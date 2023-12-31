package org.seating_arrangement_system.db.models;

public class Seat {
    private final int studentId;
    private final String studentName;
    private final String hallInfo;
    private final int roomNo;
    private final String seatId;
    private final String semester;
    private int row;
    private int column;

    public Seat(int studentId, String studentName, String hallInfo, int roomNo, String seatId, String semester) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.hallInfo = hallInfo;
        this.roomNo = roomNo;
        this.seatId = seatId;
        this.semester = semester;
        this.row = row;
        this.column= column;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getHallInfo() {
        return hallInfo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getSeatId() {
        return seatId;
    }
    public String getSemester() {return semester;}

    @Override
    public String toString() {
        return "Seat{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", hallInfo='" + hallInfo + '\'' +
                ", roomNo=" + roomNo +
                ", seatId='" + seatId + '\'' +
                '}';
    }
}
