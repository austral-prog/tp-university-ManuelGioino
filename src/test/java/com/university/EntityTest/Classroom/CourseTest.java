package com.university.EntityTest.Classroom;

import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course(101, "Mathematics");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(101, course.getClassroom());
        assertEquals("Mathematics", course.getSubject());
        assertTrue(course.getStudents().isEmpty());
    }

    @Test
    public void testAddStudent() {
        Student student1 = new Student("Alice", "alice@example.com");
        Student student2 = new Student("Bob", "bob@example.com");

        course.addStudent(student1);
        course.addStudent(student2);

        List<Student> students = course.getStudents();
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    @Test
    public void testAddDuplicateStudent() {
        Student student = new Student("Alice", "alice@example.com");

        course.addStudent(student);
        course.addStudent(student); // Adding the same student again

        List<Student> students = course.getStudents();
        assertEquals(1, students.size());
    }

    @Test
    public void testSetSubject() {
        course.setSubject("Physics");
        assertEquals("Physics", course.getSubject());
    }

    @Test
    public void testSetClassroom() {
        course.setClassroom(202);
        assertEquals(202, course.getClassroom());
    }

    @Test
    public void testSetNegativeClassroomThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            course.setClassroom(-1);
        });
        assertEquals("Classroom number cannot be negative", exception.getMessage());
    }

    @Test
    public void testSetIdAndGetId() {
        course.setId(10);
        assertEquals(10, course.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Mathematics", course.getName());
    }

    @Test
    public void testToString() {
        String expected = "Course{classroom=101, subject='Mathematics'}";
        assertEquals(expected, course.toString());
    }

    @Test
    public void testConstructorNegativeClassroomThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(-1, "History");
        });
        assertEquals("Classroom number cannot be negative", exception.getMessage());
    }
}
