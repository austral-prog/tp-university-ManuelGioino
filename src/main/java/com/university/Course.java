package com.university;

import java.util.LinkedList;
import java.util.Collection;

public class Course {
    private Collection<Student> enrolledStudents;
    private int roomNumber;

    public Course(int roomNumber) {
        this.roomNumber = roomNumber;
        this.enrolledStudents = new LinkedList<>();
    }

    public void registerStudent(Student student) {
        enrolledStudents.add(student);
    }

    public Collection<Student> listStudents() {
        return enrolledStudents;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void changeRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}

