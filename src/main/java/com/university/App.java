package com.university;

import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        String inputFilePathPart1 = "src/main/resources/input.csv";
        String outputFilePathPart1 = "src/main/resources/solution.csv";
        String inputFilePathPart2 = "src/main/resources/input_2.csv";
        String outputFilePathPart2 = "src/main/resources/solution_2.csv";

        runPart1(inputFilePathPart1, outputFilePathPart1);
        runPart2(inputFilePathPart2, outputFilePathPart2);
    }

    private static void runPart1(String inputFilePath, String outputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String[]> data = csvReader.readCSV(inputFilePath);

        CourseCounter courseCounter = new CourseCounter();
        Map<String, Integer> studentCourseCounts = courseCounter.countCourses(data);

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeCSV(outputFilePath, studentCourseCounts);
    }

    private static void runPart2(String inputFilePath, String outputFilePath) {
        CSVReader csvReader = new CSVReader();
        List<String[]> data = csvReader.readCSV(inputFilePath);

        EvaluationProcessor evaluationProcessor = new EvaluationProcessor();
        Map<String, StudentGradeInfo> studentGrades = evaluationProcessor.processEvaluations(data);

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeStudentGrades(outputFilePath, studentGrades);
    }
}
