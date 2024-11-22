package com.university.FactoryTest;

import com.university.Factory.StudentCreator;
import com.university.University.University;
import com.university.entity.Classroom.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentCreatorTest {

    private StudentCreator studentCreator;
    private University university;

    @BeforeEach
    void setUp() {
        university = new University();
        studentCreator = new StudentCreator(new ArrayList<>());
    }

    @Test
    void testCreateNewStudent() {
        String parts = "1,Math,John Doe,john.doe@example.com";

        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);

        assertNotNull(student, "Student should not be null");
        assertEquals("John Doe", student.getName(), "Student name should match");
        assertEquals("john.doe@example.com", student.getEmail(), "Student email should match");
        assertEquals(1, university.getStudents().size(), "University should contain one student");
    }

    @Test
    void testFindExistingStudent() {
        String parts = "1,Math,John Doe,john.doe@example.com";
        Student existingStudent = new Student("John Doe", "john.doe@example.com");
        university.addStudent(existingStudent);

        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);

        assertSame(existingStudent, student, "Should return the existing student object");
        assertEquals(1, university.getStudents().size(), "No duplicate students should be created");
    }

    @Test
    void testCreateStudentWithDifferentEmail() {
        String parts = "1,Math,John Doe,new.email@example.com";
        Student existingStudent = new Student("John Doe", "old.email@example.com");
        university.addStudent(existingStudent);

        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);

        assertSame(existingStudent, student, "Should return the existing student object");
        assertNotEquals("new.email@example.com", student.getEmail(), "Email should not be updated");
        assertEquals(1, university.getStudents().size(), "No duplicate students should be created");
    }

    @Test
    void testFindStudentByName() {
        String parts = "1,Math,John Doe,john.doe@example.com";
        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);

        Student foundStudent = university.getStudents().get(0);

        assertEquals("John Doe", foundStudent.getName(), "Student name should match");
        assertEquals("john.doe@example.com", foundStudent.getEmail(), "Student email should match");
    }

    @Test
    void testCreateStudentWithInvalidParts() {
        String parts = "1,Math,John Doe"; // Missing email field

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            studentCreator.getOrCreate(parts, university.getStudents(), university);
        });

        assertEquals("Index 3 out of bounds for length 3", exception.getMessage(), "Exception message should match");
    }
}
