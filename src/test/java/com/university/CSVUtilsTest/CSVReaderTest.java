package com.university.CSVUtilsTest;
import com.university.CSVUtils.CSVReader;
import com.university.TpProcessor.Common.DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    @TempDir
    File tempDir;

    @Test
    void testProcessCSVSuccess() throws IOException {
        // Configurar archivo CSV temporal
        File tempFile = new File(tempDir, "test.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Header1,Header2,Header3\n");
            writer.write("data1,data2,data3\n");
            writer.write("data4,data5,data6\n");
        }

        // Mock de DataCreator
        DataCreator mockCreator = (parts, university, criteriaProcessor) -> {
            assertNotNull(parts);
            assertNotNull(university);
            assertNotNull(criteriaProcessor);
        };

        University university = new University();
        CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

        // Ejecutar método
        assertDoesNotThrow(() -> CSVReader.processCSV(tempFile.getAbsolutePath(), university, mockCreator, criteriaProcessor));
    }

    @Test
    void testProcessCSVFileNotFound() {
        // Archivo inexistente
        String invalidFilePath = tempDir.getAbsolutePath() + "/nonexistent.csv";

        DataCreator mockCreator = (parts, university, criteriaProcessor) -> {};

        University university = new University();
        CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

        // Validar excepción lanzada
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CSVReader.processCSV(invalidFilePath, university, mockCreator, criteriaProcessor);
        });

        assertTrue(exception.getMessage().contains("The specified file was not found"));
    }

    @Test
    void testProcessCSVGeneralException() throws IOException {
        // Configurar archivo con línea inválida
        File tempFile = new File(tempDir, "test_invalid.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("Header1,Header2,Header3\n");
            writer.write("data1,data2,data3\n");
        }

        // Crear un DataCreator que lanza excepción
        DataCreator mockCreator = (parts, university, criteriaProcessor) -> {
            throw new RuntimeException("Mocked exception");
        };

        University university = new University();
        CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

        // Ejecutar y validar excepción logueada pero no lanzada
        assertDoesNotThrow(() -> CSVReader.processCSV(tempFile.getAbsolutePath(), university, mockCreator, criteriaProcessor));
    }
}
