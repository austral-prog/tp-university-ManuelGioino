package com.university.CSVUtilsTest;
import com.university.CSVUtils.Sorter;
import com.university.entity.Grading.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    @Test
    void testSortByName() {
        Map<String, Integer> data = new HashMap<>();
        data.put("Alice", 10);
        data.put("Charlie", 5);
        data.put("Bob", 15);
        List<Map.Entry<String, Integer>> sortedEntries = Sorter.sortByName(data);
        assertEquals("Alice", sortedEntries.get(0).getKey());
        assertEquals("Bob", sortedEntries.get(1).getKey());
        assertEquals("Charlie", sortedEntries.get(2).getKey());
    }
    @Test
    void testSortEvaluations() {
        PracticalWork eval1 = new PracticalWork("Parcial", "John", "Programacion", "PRACTICAL_WORK");
        OralExam eval2 = new OralExam("Parcial", "Francisco", "Fisica", "ORAL_EXAM");
        WrittenExam eval3 = new WrittenExam("Parcial", "Matias", "Algebra", "WRITTEN_EXAM");
        FinalExam eval4 = new FinalExam("Parcial", "Alice", "Analisis", "FINAL_EXAM");
        List<Evaluation> evaluations = Arrays.asList(eval1, eval2, eval3, eval4);
        Sorter.sortEvaluations(evaluations);
        assertEquals("Algebra", evaluations.get(0).getSubjectName());
        assertEquals("Parcial", evaluations.get(0).getName());
        assertEquals("Matias", evaluations.get(0).getStudentName());
        assertEquals("Analisis", evaluations.get(1).getSubjectName());
        assertEquals("Parcial", evaluations.get(1).getName());
        assertEquals("Alice", evaluations.get(1).getStudentName());
        assertEquals("Fisica", evaluations.get(2).getSubjectName());
        assertEquals("Parcial", evaluations.get(2).getName());
        assertEquals("Francisco", evaluations.get(2).getStudentName());
        assertEquals("Programacion", evaluations.get(3).getSubjectName());
        assertEquals("Parcial", evaluations.get(3).getName());
        assertEquals("John", evaluations.get(3).getStudentName());
    }
}