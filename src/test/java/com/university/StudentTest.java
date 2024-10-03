package com.university;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testRetrieveName() {
        Student student = new Student("John Doe", 3, "john@example.com");
        assertEquals("John Doe", student.retrieveName(), "The name should be 'John Doe'.");
    }

    @Test
    void testCountCourses() {
        Student student = new Student("Jane Smith", 5, "jane@example.com");
        assertEquals(5, student.countCourses(), "The total number of courses should be 5.");
    }

    @Test
    void testGetEmailAddress() {
        Student student = new Student("Alice Johnson", 2, "alice@example.com");
        assertEquals("alice@example.com", student.getEmailAddress(), "The email should be 'alice@example.com'.");
    }
}
