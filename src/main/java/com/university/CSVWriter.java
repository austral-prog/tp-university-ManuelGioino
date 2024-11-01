package com.university;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CSVWriter {

    // Método para escribir la Parte 1: Student_Name y Course_Count
    public void writeCSV(String filePath, Map<String, Integer> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Student_Name,Course_Count\n");

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(data.entrySet());
            sortedEntries.sort(Map.Entry.comparingByKey());

            for (Map.Entry<String, Integer> entry : sortedEntries) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para escribir la Parte 2: Subject_Name, Evaluation_Name, Student_Name, Grade
    public void writeStudentGrades(String filePath, Map<String, StudentGradeInfo> studentGrades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            StudentSorter sorter = new StudentSorter();
            List<StudentGradeInfo> sortedGrades = sorter.sortBySubjectAndName(studentGrades);

            for (StudentGradeInfo gradeInfo : sortedGrades) {
                writer.write(gradeInfo.getSubjectName() + "," +
                        gradeInfo.getEvaluationName() + "," +
                        gradeInfo.getStudentName() + "," +
                        gradeInfo.getFinalGrade() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
