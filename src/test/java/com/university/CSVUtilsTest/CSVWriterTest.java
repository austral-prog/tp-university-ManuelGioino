package com.university.CSVUtilsTest;

import com.university.CSVUtils.CSVWriter;
import com.university.University.University;
import com.university.TpProcessor.Common.DataWriter;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CSVWriterTest {

    private String filePath;

    @BeforeEach
    public void setUp() {
        filePath = "test_output.csv";
    }

    @Test
    public void testWriteCSV() throws IOException {
        // Crear un DataWriter simulado
        DataWriter dataWriter = (university, criteriaProcessor, writer) -> {
            writer.write("Header\n");
            writer.write("Row1,Value1\n");
            writer.write("Row2,Value2\n");
        };

        // Crear objetos University y CriteriaProcessor vacíos
        University university = new University();
        CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

        // Ejecutar el método
        CSVWriter.writeCSV(filePath, university, criteriaProcessor, dataWriter);

        // Verificar que el archivo existe
        assertTrue(Files.exists(Paths.get(filePath)));

        // Leer el contenido del archivo
        String content = Files.readString(Paths.get(filePath));
        assertEquals("Header\nRow1,Value1\nRow2,Value2\n", content);

        // Limpiar el archivo generado
        Files.deleteIfExists(Paths.get(filePath));
    }

    @Test
    public void testWriteCSVWithException() {
        // Crear un DataWriter simulado que lanza una excepción
        DataWriter dataWriter = (university, criteriaProcessor, writer) -> {
            throw new IOException("Simulated IOException");
        };

        University university = new University();
        CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

        // Ejecutar el método y capturar la excepción
        Exception exception = assertThrows(IOException.class, () -> {
            CSVWriter.writeCSV(filePath, university, criteriaProcessor, dataWriter);
        });

        // Verificar que se lanzó la excepción correcta
        assertEquals("Simulated IOException", exception.getMessage());
    }
}

