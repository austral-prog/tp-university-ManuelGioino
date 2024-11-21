package com.university.TpProcessor.Part1;

import com.university.TpProcessor.Common.DataWriter;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.CSVUtils.Sorter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Part1Writer implements DataWriter {
    @Override
    public void write(University university, CriteriaProcessor criteriaProcessor, BufferedWriter writer) throws IOException {
        writer.write("Student_Name,Course_Count\n");
        Map<String, Integer> studentCounts = university.getStudentCourseCounts();
        List<Map.Entry<String, Integer>> sortedEntries = Sorter.sortByName(studentCounts);
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            writer.write(entry.getKey() + "," + entry.getValue() + "\n");
        }
    }
}