package com.university.CSVUtils;

import com.university.entity.Grading.Evaluation;

import java.util.*;

public class Sorter {

    public static List<Map.Entry<String, Integer>> sortByName(Map<String, Integer> data) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(data.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());
        return sortedEntries;
    }

    public static List<Evaluation> sortEvaluations(List<Evaluation> evaluations) {
        List<String> evaluationOrder = Arrays.asList(
                "Examen Final", "Primer Parcial", "Segundo Parcial", "TP Final",
                "TP1", "TP10", "TP2", "TP3", "TP4", "TP5", "TP6", "TP7", "TP8", "TP9"
        );

        evaluations.sort(Comparator
                .comparing(Evaluation::getSubjectName)
                .thenComparing(evaluation -> evaluationOrder.indexOf(evaluation.getName()))
                .thenComparing(Evaluation::getStudentName)
        );
        return evaluations;
    }
}
