package com.university.entity.Classroom;

import com.university.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Course implements Entity {

    private static int idCounter = 0;
    private int id;
    private List<Student> students;
    private String subject;
    private int classroom;

    public Course(){}

    public Course(int classroom, String subject) {
        if (classroom < 0) {
            throw new IllegalArgumentException("Classroom number cannot be negative");
        }
        this.id = idCounter++;
        this.classroom = classroom;
        this.students = new ArrayList<>();
        this.subject = subject;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        if (classroom < 0) {
            throw new IllegalArgumentException("Classroom number cannot be negative");
        }
        this.classroom = classroom;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.subject;
    }

    @Override
    public String toString() {
        return "Course{" +
                "classroom=" + classroom +
                ", subject='" + subject + '\'' +
                '}';
    }

}