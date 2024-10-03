package com.university;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;

class CourseTest {

    @Test
    void testRegisterStudent() {
        Course course = new Course(101);
        Student student = new Student("John Doe", 3, "john@example.com");

        course.registerStudent(student);
        Collection<Student> enrolledStudents = course.listStudents();

        assertTrue(enrolledStudents.contains(student), "Student should be registered in the course.");
    }

    @Test
    void testChangeRoom() {
        Course course = new Course(101);
        course.changeRoom(202);
        int roomNumber = course.getRoomNumber();
        assertEquals(202, roomNumber, "Room number should be changed to 202.");
    }

    @Test
    void testListStudents() {
        Course course = new Course(101);
        Student student1 = new Student("John Doe", 3, "john@example.com");
        Student student2 = new Student("Jane Smith", 2, "jane@example.com");

        course.registerStudent(student1);
        course.registerStudent(student2);
        Collection<Student> enrolledStudents = course.listStudents();
        assertEquals(2, enrolledStudents.size(), "There should be 2 students registered.");
        assertTrue(enrolledStudents.contains(student1), "John Doe should be in the course.");
        assertTrue(enrolledStudents.contains(student2), "Jane Smith should be in the course.");
    }
}
