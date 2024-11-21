package com.university.CLI;

import com.university.entity.Classroom.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentCRUDRepository implements CRUDRepository<Student> {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void create(Student student) {
        students.add(student);
    }

    @Override
    public Student read(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(int id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student createEntityFromCLI(Scanner scanner) {
        System.out.println("Enter student name:");
        String name = scanner.next();
        System.out.println("Enter student email:");
        String email = scanner.next();
        return new Student(name, email);
    }

    @Override
    public Student updateEntityFromCLI(Scanner scanner, Student existingStudent) {
        System.out.println("Enter new student name:");
        String newName = scanner.next();
        System.out.println("Enter new student email:");
        String newEmail = scanner.next();
        existingStudent.setName(newName);
        existingStudent.setEmail(newEmail);
        return existingStudent;
    }
}
