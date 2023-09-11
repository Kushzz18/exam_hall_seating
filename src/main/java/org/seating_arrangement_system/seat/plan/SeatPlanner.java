package org.seating_arrangement_system.seat.plan;

import org.seating_arrangement_system.db.dao.HallDao;
import org.seating_arrangement_system.db.dao.RoomDao;
import org.seating_arrangement_system.db.dao.SeatDao;
import org.seating_arrangement_system.db.dao.StudentDao;
import org.seating_arrangement_system.db.models.Hall;
import org.seating_arrangement_system.db.models.Room;
import org.seating_arrangement_system.db.models.Seat;
import org.seating_arrangement_system.db.models.Student;

import java.util.*;

public class SeatPlanner {

    public void truncate() {
        new SeatDao().truncate();
    }

    public void plan() {
        RoomDao roomDao = new RoomDao();
        StudentDao studentDao = new StudentDao();
        HallDao hallDao = new HallDao();

        List<Hall> hallList = hallDao.getAll();

        Map<Hall, List<Room>> hallWithRoom = new HashMap<>();

        hallList.forEach(hall -> {
            List<Room> rooms = roomDao.getAll(hall.getId());
            hallWithRoom.put(hall, rooms);
        });

        Queue<Student> studentQ = new LinkedList<>(studentDao.getAll());

        fitStudent(hallWithRoom, studentQ);
    }

    private void fitStudent(Map<Hall, List<Room>> hallMap, Queue<Student> students) {
        // Separate students by semester
        Map<String, List<Student>> studentsBySemester = new HashMap<>();
        for (Student student : students) {
            String semester = student.getSemester();
            studentsBySemester.computeIfAbsent(semester, k -> new ArrayList<>()).add(student);
        }

        hallMap.forEach((hall, rooms) -> {
            for (Room room : rooms) {
                assignSeatsToRoom(hall, room, studentsBySemester);
            }
        });
    }
    private void assignSeatsToRoom(Hall hall, Room room, Map<String, List<Student>> studentsBySemester) {
        List<Character> columnNames = new ArrayList<>();
        for (int i = 0; i < room.getColumnNumber(); i++) {
            columnNames.add((char) (65 + i)); // A corresponds to column 1, B corresponds to column 2, and so on
        }

        List<String> seatIdentifiers = new ArrayList<>();
        for (int j = 1; j <= room.getBenchNumber(); j++) {
            seatIdentifiers.add(j + "X");
            seatIdentifiers.add(j + "Y");
        }

        // Create a map to track assigned benches for each semester
        Map<String, String> assignedBenchesBySemester = new HashMap<>();

        for (Character colName : columnNames) {
            for (String seatIdentifier : seatIdentifiers) {
                if (studentsBySemester.isEmpty()) return;

                // Get the current seat index
                String currentSeat = colName + seatIdentifier;

                // If there is a student from the same semester on the bench, then skip this seat
                if (assignedBenchesBySemester.containsKey(currentSeat)) continue;

                // Assign the seat to a student from a different semester
                List<String> availableSemesters = getAvailableSemesters(studentsBySemester, assignedBenchesBySemester);
                if (!availableSemesters.isEmpty()) {
                    String selectedSemester = availableSemesters.get(0);
                    List<Student> students = studentsBySemester.get(selectedSemester);
                    if (!students.isEmpty()) {
                        Student student = students.remove(0);
                        assignSeat(hall, student, room, colName, seatIdentifier, assignedBenchesBySemester);
                    }
                }
            }
            assignedBenchesBySemester.clear();
        }
    }

    private List<String> getAvailableSemesters(Map<String, List<Student>> studentsBySemester, Map<String, String> assignedBenchesBySemester) {
        List<String> availableSemesters = new ArrayList<>();
        for (String semester : studentsBySemester.keySet()) {
            boolean isAssigned = false;
            if (studentsBySemester.get(semester).isEmpty()) continue; // Skip this semester if there are no available students

            for (String seat : assignedBenchesBySemester.keySet()) {
                if (assignedBenchesBySemester.get(seat).equals(semester)) {
                    isAssigned = true;
                    break;
                }
            }
            if (!isAssigned) {
                availableSemesters.add(semester);
            }
        }
        return availableSemesters;
    }
    private void assignSeat(Hall hall, Student student, Room room, char colName, String seatIdentifier, Map<String, String> assignedBenchesBySemester) {
        Seat seatInfo = new Seat(
                student.getId(),
                student.getName(),
                hall.getName(),
                room.getRoomNumber(),
                colName + seatIdentifier,
                student.getSemester()
        );
        SeatDao seatDao = new SeatDao();
        seatDao.insertSeat(seatInfo);

        // Update the tracking map. If the seat identifier is X, change it to Y (for example, from A1X to A1Y)
        String oppositeSeat = colName + (seatIdentifier.equals("X") ? "Y" : "X");
        // Store the semester of the student in the tracking map for the opposite seat
        assignedBenchesBySemester.put(oppositeSeat, student.getSemester());
    }

}
