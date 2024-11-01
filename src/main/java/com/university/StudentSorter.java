package com.university;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StudentSorter {

    public List<StudentGradeInfo> sortBySubjectAndName(Map<String, StudentGradeInfo> studentGrades) {
        // Extraer los valores de studentGrades a una lista
        List<StudentGradeInfo> sortedList = new ArrayList<>(studentGrades.values());

        // Ordenar la lista usando Collections.sort con un Comparator
        Collections.sort(sortedList, new Comparator<StudentGradeInfo>() {
            @Override
            public int compare(StudentGradeInfo a, StudentGradeInfo b) {
                int subjectComparison = a.getSubjectName().compareTo(b.getSubjectName());
                if (subjectComparison != 0) {
                    return subjectComparison;  // Ordena por materia primero
                } else {
                    return a.getStudentName().compareTo(b.getStudentName());  // Luego por nombre de estudiante
                }
            }
        });

        return sortedList;
    }
}
