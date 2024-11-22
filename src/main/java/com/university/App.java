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

    public static void processPart1(University university, CriteriaProcessor criteriaProcessor) throws Exception {
        DataCreator part1DataCreator = new Part1DataCreator();
        DataWriter part1Writer = new Part1Writer();
        CSVReader.processCSV("src/main/resources/input.csv", university, part1DataCreator, criteriaProcessor);
        CSVWriter.writeCSV("src/main/resources/solution.csv", university, criteriaProcessor, part1Writer);
    }

    public static void processPart2(University university, CriteriaProcessor criteriaProcessor) throws Exception {
        DataCreator part2DataCreator = new Part2DataCreator();
        DataWriter part2Writer = new Part2Writer();
        CSVReader.processCSV("src/main/resources/input_2.csv", university, part2DataCreator, criteriaProcessor);
        CSVWriter.writeCSV("src/main/resources/solution_2.csv", university, criteriaProcessor, part2Writer);
    }

    public static void processPart3(University university, CriteriaProcessor criteriaProcessor) throws Exception {
        DataCreator part3DataCreator = new Part3DataCreator();
        DataWriter part3Writer = new Part3Writer();
        CSVReader.processCSV("src/main/resources/input_3.csv", university, part3DataCreator, criteriaProcessor);
        CSVWriter.writeCSV("src/main/resources/solution_3.csv", university, criteriaProcessor, part3Writer);
    }

    public static void main(String[] args) {
        try {
            University university = new University();
            CriteriaProcessor criteriaProcessor = new CriteriaProcessor();

            processPart1(university, criteriaProcessor);
            processPart2(university, criteriaProcessor);
            processPart3(university, criteriaProcessor);

            System.out.println("CSV processed and written successfully.");
        } catch (Exception e) {
            System.err.println("Error when generating " + e.getMessage());
            e.printStackTrace();
        }
    }
}
