package com.university.TpProcessorTest.Part3;

import com.university.TpProcessor.Part3.Part3Writer;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.entity.Grading.criteria.Criterion;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.WrittenExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Part3WriterTest {

    private Part3Writer part3Writer;
    private University university;
    private CriteriaProcessor criteriaProcessor;
    private String filePath;

    @BeforeEach
    void setUp() {
        part3Writer = new Part3Writer();
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
        filePath = "test_output_part3.csv";
    }

    @Test
    void testWriteHandlesEmptyData() throws IOException {
        // Act
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            part3Writer.write(university, criteriaProcessor, writer);
        }

        // Assert
        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertEquals(1, lines.size(), "The file should only contain the header when no data is provided.");
        assertEquals("Student,Subject,Status", lines.get(0), "The header should match the expected format.");

        // Cleanup
        Files.delete(Path.of(filePath));
    }

    @Test
    void testWriteValidData() throws IOException {
        // Arrange
        // Add criteria for a specific subject
        criteriaProcessor.addCriteria(new Criterion(
                "Math", "AVERAGE_ABOVE_VALUE", 5.0, List.of("Exam1", "Exam2")
        ));

        // Add students to the university
        Student alice = new Student("Alice", "alice@example.com");
        Student bob = new Student("Bob", "bob@example.com");

        // Add evaluations for the students
        WrittenExam exam1 = new WrittenExam("Exam1", "Alice", "Math", "WRITTEN_EXAM");
        exam1.addGrades(6.0); // Alice passes

        WrittenExam exam2 = new WrittenExam("Exam2", "Bob", "Math", "WRITTEN_EXAM");
        exam2.addGrades(4.0); // Bob fails

        alice.addEvaluation(exam1);
        bob.addEvaluation(exam2);

        university.addStudent(alice);
        university.addStudent(bob);

        // Act
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            part3Writer.write(university, criteriaProcessor, writer);
        }

        // Assert
        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertTrue(lines.size() > 1, "The file should contain more than just the header.");
        assertEquals("Student,Subject,Status", lines.get(0), "The header should match the expected format.");
        assertTrue(lines.stream().anyMatch(line -> line.contains("Alice") && line.contains("PASSED")), "Results should include Alice as passed.");
        assertTrue(lines.stream().anyMatch(line -> line.contains("Bob") && line.contains("FAILED")), "Results should include Bob as failed.");

        // Cleanup
        Files.delete(Path.of(filePath));
    }

    @Test
    void testWriteHandlesIOException() {
        // Arrange
        String invalidFilePath = "/invalid_directory/test_output_part3.csv";

        // Act & Assert
        Exception exception = assertThrows(IOException.class, () -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFilePath))) {
                part3Writer.write(university, criteriaProcessor, writer);
            }
        });
        assertNotNull(exception.getMessage(), "IOException should have a message.");
    }
}


