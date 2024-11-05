package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StudentSorter {

    public List<StudentGradeInfo> sortBySubjectAndName(Map<String, StudentGradeInfo> studentGrades) {
        List<StudentGradeInfo> sortedGrades = new ArrayList<>(studentGrades.values());

        sortedGrades.sort(Comparator.comparing(StudentGradeInfo::getSubjectName)
                .thenComparing(grade -> {
                    String evalName = grade.getEvaluationName();
                    switch (evalName.toUpperCase()) {
                        case "TP FINAL": return 0;
                        case "EXAMEN FINAL": return 1;
                        case "PRIMER PARCIAL": return 2;
                        case "SEGUNDO PARCIAL": return 3;
                        case "TP1": return 4;
                        case "TP10": return 5;
                        case "TP2": return 6;
                        case "TP3": return 7;
                        case "TP4": return 8;
                        case "TP5": return 9;
                        case "TP6": return 10;
                        case "TP7": return 11;
                        case "TP8": return 12;
                        case "TP9": return 13;
                        default: return 30;
                    }
                })
                .thenComparing(StudentGradeInfo::getStudentName));

        return sortedGrades;
    }
}
