package com.university;


import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.University.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private University university;
    private CriteriaProcessor criteriaProcessor;

    @BeforeEach
    void setUp() {
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
    }

    @Test
    void testProcessPart1() throws Exception {
        // Act
        App.processPart1(university, criteriaProcessor);

        // Assert
        Path outputPath = Path.of("src/main/resources/solution.csv");
        assertTrue(Files.exists(outputPath), "Solution file for Part 1 should be created.");
        assertTrue(university.getStudents().size() > 0, "University should have students after Part 1.");
        Files.delete(outputPath); // Cleanup
    }

    @Test
    void testProcessPart2() throws Exception {
        // Act
        App.processPart2(university, criteriaProcessor);

        // Assert
        Path outputPath = Path.of("src/main/resources/solution_2.csv");
        assertTrue(Files.exists(outputPath), "Solution file for Part 2 should be created.");
        assertTrue(university.getEvaluations().size() > 0, "University should have evaluations after Part 2.");
        Files.delete(outputPath); // Cleanup
    }

    @Test
    void testProcessPart3() throws Exception {
        // Act
        App.processPart3(university, criteriaProcessor);

        // Assert
        Path outputPath = Path.of("src/main/resources/solution_3.csv");
        assertTrue(Files.exists(outputPath), "Solution file for Part 3 should be created.");
        Files.delete(outputPath); // Cleanup
    }

    @Test
    void testMainExecutesWithoutException() {
        // Act & Assert
        assertDoesNotThrow(() -> App.main(new String[]{}), "Main method should execute without exceptions.");
    }
}
