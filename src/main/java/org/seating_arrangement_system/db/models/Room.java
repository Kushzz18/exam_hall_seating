package org.seating_arrangement_system.db.models;

public class Room {
    private final int hallId;
    private final int roomNumber;
    private final int columnNumber;
    private final int benchNumber;
    private final int totalCapacity;

    public Room(int hallId, int roomNumber, int columnNumber, int benchNumber) {
        this.hallId = hallId;
        this.roomNumber = roomNumber;
        this.columnNumber = columnNumber;
        this.benchNumber = benchNumber;
        totalCapacity = benchNumber * columnNumber * 2;
    }

    public int getHallId() {
        return hallId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getBenchNumber() {
        return benchNumber;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
