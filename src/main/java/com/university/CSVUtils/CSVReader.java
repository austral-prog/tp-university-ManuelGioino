package com.university.CSVUtils;

import com.university.TpProcessor.Common.DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVReader {

    public static void processCSV(String filePath, University university, DataCreator creator, CriteriaProcessor criteriaProcessor) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String parts = line;
                creator.create(parts, university, criteriaProcessor);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filePath);
            throw new IllegalArgumentException("The specified file was not found: " + filePath, e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}