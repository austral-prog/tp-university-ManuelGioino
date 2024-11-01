package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            bufferedReader.readLine(); // Saltar la línea de cabecera

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] row = currentLine.split(",");
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
