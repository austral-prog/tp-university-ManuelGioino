package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class App {

    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input.csv";
        String outputFilePath = "src/main/resources/solution.csv";

        Map<String, Integer> studentCourseCounts = processCSV(inputFilePath);
        writeOutputCSV(outputFilePath, studentCourseCounts);
    }

    private static Map<String, Integer> processCSV(String filePath) {
        Map<String, Set<String>> studentToCoursesMap = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            bufferedReader.readLine(); // Skip header line

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] data = currentLine.split(",");
                String student = data[2].trim();
                String course = data[1].trim();

                // Initialize set if student is not present in map
                studentToCoursesMap.computeIfAbsent(student, k -> new HashSet<>()).add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert set sizes to counts
        Map<String, Integer> studentCourseCountMap = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : studentToCoursesMap.entrySet()) {
            studentCourseCountMap.put(entry.getKey(), entry.getValue().size());
        }

        return studentCourseCountMap;
    }

    private static void writeOutputCSV(String filePath, Map<String, Integer> studentCounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Student_Name,Course_Count\n");
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(studentCounts.entrySet());
            sortedEntries.sort(Map.Entry.comparingByKey());

            for (Map.Entry<String, Integer> entry : sortedEntries) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
//            writer.write("\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
