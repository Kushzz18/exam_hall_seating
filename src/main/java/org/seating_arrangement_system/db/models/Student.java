package org.seating_arrangement_system.db.models;

public class Student {
    private final int id;
    private final String name;

    private String Semester;
    public Student(int id, String name,String semester) {
        this.id = id;
        this.name = name;
        this.Semester = semester;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
    public String getSemester() {
        return Semester;
    }
}
