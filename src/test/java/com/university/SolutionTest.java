package com.university;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {

    @Test
    public void testSolutionCSVMatchesExpected() {
        String solutionFilePath = "src/main/resources/solution.csv";
        String expectedFilePath = "src/main/resources/expected.csv";
        Path solutionPath = Paths.get(solutionFilePath);

        // Borrar solution.csv si existe antes de ejecutar el test
        try {
            Files.deleteIfExists(solutionPath);
        } catch (IOException e) {
            e.printStackTrace();
            fail("No se pudo eliminar solution.csv antes de la ejecución del test.");
        }

        // Verificar que solution.csv no exista antes de ejecutar el test
        if (Files.exists(solutionPath)) {
            fail("El archivo solution.csv existe antes de la ejecución del test, incluso después de intentar eliminarlo.");
        }

        // Ejecutar el método main de App
        try {
            App.main(new String[]{});  // Ejecutar el método main de App para generar solution.csv
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar App.main()");
        }

        // Verificar que solution.csv fue creado después de ejecutar el test
        if (!Files.exists(solutionPath)) {
            fail("El archivo solution.csv no existe después de ejecutar el test.");
        }

        // Comparar solution.csv con expected.csv
        try (BufferedReader solutionReader = new BufferedReader(new FileReader(solutionFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {

            String solutionLine;
            String expectedLine;

            while ((solutionLine = solutionReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                assertEquals(expectedLine, solutionLine, "Diferencia encontrada en el contenido del archivo CSV de la Parte 1.");
            }

            assertEquals(solutionReader.readLine(), expectedReader.readLine(), "Los archivos tienen diferente número de líneas en la Parte 1.");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Ocurrió un error al comparar los archivos CSV de la Parte 1.");
        }
    }

    @Test
    public void testSolution2CSVMatchesExpected2() {
        String solutionFilePath = "src/main/resources/solution_2.csv";
        String expectedFilePath = "src/main/resources/expected_2.csv";
        Path solutionPath = Paths.get(solutionFilePath);

        // Borrar solution_2.csv si existe antes de ejecutar el test
        try {
            Files.deleteIfExists(solutionPath);
        } catch (IOException e) {
            e.printStackTrace();
            fail("No se pudo eliminar solution_2.csv antes de la ejecución del test.");
        }

        // Verificar que solution_2.csv no exista antes de ejecutar el test
        if (Files.exists(solutionPath)) {
            fail("El archivo solution_2.csv existe antes de la ejecución del test, incluso después de intentar eliminarlo.");
        }

        // Ejecutar el método main de App
        try {
            App.main(new String[]{});  // Ejecutar el método main de App para generar solution_2.csv
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar App.main()");
        }

        // Verificar que solution_2.csv fue creado después de ejecutar el test
        if (!Files.exists(solutionPath)) {
            fail("El archivo solution_2.csv no existe después de ejecutar el test.");
        }

        // Comparar solution_2.csv con expected_2.csv
        try (BufferedReader solutionReader = new BufferedReader(new FileReader(solutionFilePath));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath))) {

            String solutionLine;
            String expectedLine;

            while ((solutionLine = solutionReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                assertEquals(expectedLine, solutionLine, "Diferencia encontrada en el contenido del archivo CSV de la Parte 2.");
            }

            assertEquals(solutionReader.readLine(), expectedReader.readLine(), "Los archivos tienen diferente número de líneas en la Parte 2.");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Ocurrió un error al comparar los archivos CSV de la Parte 2.");
        }
    }
}
