package com.university.TpProcessorTest.Part2;

import com.university.TpProcessor.Part2.Part2Writer;
import com.university.University.University;
import com.university.entity.Grading.WrittenExam;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Part2WriterTest {

    private Part2Writer part2Writer;
    private University university;
    private CriteriaProcessor criteriaProcessor;
    private String filePath;

    @BeforeEach
    void setUp() throws IOException {
        part2Writer = new Part2Writer();
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
        filePath = "test_output_part2.csv";
        Files.deleteIfExists(Path.of(filePath)); // Ensure clean test environment
    }

    @Test
    void testWriteWithEmptyEvaluations() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            part2Writer.write(university, criteriaProcessor, writer);
        }

        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertEquals(1, lines.size(), "Only the header should be present");
        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", lines.get(0));
    }

    @Test
    void testWriteWithEvaluations() throws IOException {
        // Add mock evaluations
        university.addEvaluation(new WrittenExam("Primer Parcial", "John Doe", "Math", "WRITTEN_EXAM"));
        university.getEvaluations().get(0).addGrades(8.0);

        university.addEvaluation(new WrittenExam("Segundo Parcial", "Alice", "Math", "WRITTEN_EXAM"));
        university.getEvaluations().get(1).addGrades(7.5);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            part2Writer.write(university, criteriaProcessor, writer);
        }

        List<String> lines = Files.readAllLines(Path.of(filePath));
        assertEquals(3, lines.size(), "Header and two evaluation rows should be present");
        assertEquals("Subject_Name,Evaluation_Name,Student_Name,Grade", lines.get(0));
        assertEquals("Math,Primer Parcial,John Doe,8.0", lines.get(1));
        assertEquals("Math,Segundo Parcial,Alice,7.5", lines.get(2));
    }

    @Test
    void testWriteWithIOException() {
        String invalidFilePath = "/invalid_path/output.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(invalidFilePath))) {
            part2Writer.write(university, criteriaProcessor, writer);
            fail("Expected IOException was not thrown");
        } catch (IOException e) {
            assertNotNull(e.getMessage(), "IOException message should not be null");
        }
    }
}
