package com.university;

import com.university.TpProcessor.Common.DataCreator;
import com.university.TpProcessor.Common.DataWriter;

import com.university.TpProcessor.Part1.Part1DataCreator;
import com.university.TpProcessor.Part1.Part1Writer;

import com.university.TpProcessor.Part2.Part2DataCreator;
import com.university.TpProcessor.Part2.Part2Writer;

import com.university.TpProcessor.Part3.Part3DataCreator;
import com.university.TpProcessor.Part3.Part3Writer;

import com.university.CSVUtils.CSVReader;
import com.university.CSVUtils.CSVWriter;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;

public class App {

    public static void main(String[] args) {
        try {
            University university = new University();

            DataCreator part1DataCreator = new Part1DataCreator();
            DataCreator part2DataCreator = new Part2DataCreator();
            DataCreator part3DataCreator = new Part3DataCreator();
            DataWriter part1Writer = new Part1Writer();
            DataWriter part2Writer = new Part2Writer();
            DataWriter part3Writer = new Part3Writer();

            CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

            // Procesar y escribir para la Parte 1
            CSVReader.processCSV("src/main/resources/input.csv", university, part1DataCreator, criteriaProcessor);
            CSVWriter.writeCSV("src/main/resources/solution.csv", university, criteriaProcessor, part1Writer);

            // Procesar y escribir para la Parte 2
            CSVReader.processCSV("src/main/resources/input_2.csv", university, part2DataCreator, criteriaProcessor);
            System.out.println("Evaluations after processing Part 2: " + university.getEvaluations().size());
            CSVWriter.writeCSV("src/main/resources/solution_2.csv", university, criteriaProcessor, part2Writer);

            // Procesar y escribir para la Parte 3
            CSVReader.processCSV("src/main/resources/input_3.csv", university, part3DataCreator, criteriaProcessor);
            CSVWriter.writeCSV("src/main/resources/solution_3.csv", university, criteriaProcessor, part3Writer);

            System.out.println("CSV processed and written successfully.");
        } catch (Exception e) {
            System.err.println("Error when generating " + e.getMessage());
            e.printStackTrace();
        }
    }
}
