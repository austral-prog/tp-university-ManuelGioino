package com.university;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

    private boolean compareCSVFiles(String generatedFilePath, String expectedFilePath) {
        try (BufferedReader generatedReader = new BufferedReader(new FileReader(generatedFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {

            String generatedLine;
            String expectedLine;

            while ((generatedLine = generatedReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                if (!generatedLine.equals(expectedLine)) {
                    return false; // Las líneas no coinciden
                }
            }

            // Verificar si ambos archivos terminaron simultáneamente
            return generatedReader.readLine() == null && expectedReader.readLine() == null;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Test
    public void testSolutionCSVMatchesExpected() {
        String solutionFilePath = "src/main/resources/solution.csv";
        String expectedFilePath = "src/main/resources/expected.csv";

        assertTrue(compareCSVFiles(solutionFilePath, expectedFilePath),
                "The generated solution.csv file does not match the expected.csv file.");
    }

    @Test
    public void testSolution2CSVMatchesExpected() {
        String solution2FilePath = "src/main/resources/solution_2.csv";
        String expected2FilePath = "src/main/resources/expected_2.csv";

        assertTrue(compareCSVFiles(solution2FilePath, expected2FilePath),
                "The generated solution_2.csv file does not match the expected_2.csv file.");
    }
}
