package com.university.TpProcessorTest.Part1;


import com.university.University.University;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Part1WriterTest {

    private University university;
    private CriteriaProcessor criteriaProcessor;
    private com.university.TpProcessor.Part1.Part1Writer part1Writer;
    private String outputFilePath;

    @BeforeEach
    void setUp() {
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
        part1Writer = new com.university.TpProcessor.Part1.Part1Writer();
        outputFilePath = "test_output.csv";
    }

    @Test
    void testWriteWithValidData() throws IOException {
        // Prepare test data
        Student student1 = new Student("Alice", "alice@example.com");
        Student student2 = new Student("Bob", "bob@example.com");
        student1.addToCourse(new com.university.entity.Classroom.Course(101, "Math"));
        student2.addToCourse(new com.university.entity.Classroom.Course(102, "Physics"));
        student2.addToCourse(new com.university.entity.Classroom.Course(103, "Chemistry"));
        university.addStudent(student1);
        university.addStudent(student2);

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            part1Writer.write(university, criteriaProcessor, writer);
        }

        // Validate file content
        List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
        assertEquals(3, lines.size()); // Header + 2 students
        assertEquals("Student_Name,Course_Count", lines.get(0), "Header should match");
        assertEquals("Alice,1", lines.get(1), "First student data should match");
        assertEquals("Bob,2", lines.get(2), "Second student data should match");

        // Clean up
        Files.deleteIfExists(Paths.get(outputFilePath));
    }

    @Test
    void testWriteWithNoStudents() throws IOException {
        // Write to file with no students in the university
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            part1Writer.write(university, criteriaProcessor, writer);
        }

        // Validate file content
        List<String> lines = Files.readAllLines(Paths.get(outputFilePath));
        assertEquals(1, lines.size(), "File should contain only the header");
        assertEquals("Student_Name,Course_Count", lines.get(0), "Header should match");

        // Clean up
        Files.deleteIfExists(Paths.get(outputFilePath));
    }

    @Test
    void testWriteIOException() {
        // Provide an invalid file path
        String invalidFilePath = "/invalid_directory/output.csv";

        IOException exception = assertThrows(IOException.class, () -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFilePath))) {
                part1Writer.write(university, criteriaProcessor, writer);
            }
        });

        assertNotNull(exception.getMessage(), "Exception message should not be null");
    }
}
