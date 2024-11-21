package com.university.entity.Grading.criteria;

import com.university.University.University;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;

import java.util.*;

public class CriteriaProcessor {
    private List<Criterion> criteriaList = new ArrayList<>();

    public void addCriteria(Criterion criteria) {
        criteriaList.add(criteria);
    }

    public List<Criterion> getCriteriaList() {
        return criteriaList;
    }

    private Criterion findCriterionForSubject(String subject) {
        for (Criterion criterion : criteriaList) {
            if (criterion.getSubjectName().equals(subject)) {
                return criterion;
            }
        }
        return null;
    }

    public List<String> evaluateStudents(University university) {
        Set<String> uniqueResults = new TreeSet<>(); // TreeSet asegura unicidad y orden alfabético

        for (Student student : university.getStudents()) {
            Map<String, List<Evaluation>> subjectEvaluations = new HashMap<>();

            // Agrupar evaluaciones por materia
            for (Evaluation evaluation : student.getEvaluations()) {
                String subject = evaluation.getSubjectName();
                subjectEvaluations.computeIfAbsent(subject, k -> new ArrayList<>()).add(evaluation);
            }

            // Evaluar criterios para cada materia
            for (Map.Entry<String, List<Evaluation>> entry : subjectEvaluations.entrySet()) {
                String subject = entry.getKey();
                Criterion criterion = findCriterionForSubject(subject);
                boolean passed = criterion != null && criterion.evaluate(student);
                String status = passed ? "PASSED" : "FAILED";

                uniqueResults.add(student.getName() + "," + subject + "," + status);
            }
        }
        return new ArrayList<>(uniqueResults); // Convertir TreeSet a lista
    }
}
