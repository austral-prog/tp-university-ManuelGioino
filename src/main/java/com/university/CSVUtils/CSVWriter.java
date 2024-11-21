package com.university.CSVUtils;

import com.university.University.University;

import java.io.BufferedWriter;
import com.university.TpProcessor.Common.DataWriter;
import com.university.entity.Grading.criteria.CriteriaProcessor;

import java.io.*;

public class CSVWriter {

    public static void writeCSV(String filePath, University university, CriteriaProcessor criteriaProcessor, DataWriter fileWriter) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            fileWriter.write(university, criteriaProcessor, writer);
        }
    }
}