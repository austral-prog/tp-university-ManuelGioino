package com.university.Factory;

import com.university.University.University;
import com.university.entity.Classroom.Student;

import java.util.List;

public class StudentCreator implements EntityCreator<Student>{

    private List<Student> students;

    public StudentCreator(List<Student> students){
        this.students = students;
    }

    public Student getOrCreate(String parts, List<Student> students, University university) {
        String[] params = parts.split(",");
        String studentName = params[2];
        String email = params[3];
        Student student = findStudentByName(studentName, university);
        if (student == null) {
            student = new Student(studentName, email);
            university.getStudents().add(student);
        }
        return student;
    }

    private Student findStudentByName(String name, University university) {
        for (Student student : university.getStudents()) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
}
