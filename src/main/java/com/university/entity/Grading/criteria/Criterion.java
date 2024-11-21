package com.university.entity.Grading.criteria;

import com.university.entity.Classroom.Student;

import java.util.List;

public class Criterion {
    private String subjectName;
    private String criteriaType;
    private double criteriaValue;
    private List<String> evaluationNames;

    public Criterion(String subjectName, String criteriaType, double criteriaValue, List<String> evaluationNames) {
        this.subjectName = subjectName;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.evaluationNames = evaluationNames;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public boolean evaluate(Student student) {
        double result;
        switch (criteriaType) {
            case "AVERAGE_ABOVE_VALUE":
                result = student.calculateAverage(subjectName, evaluationNames);
                return result >= criteriaValue;
            case "MAX_ABOVE_VALUE":
                result = student.getMaxScore(subjectName, evaluationNames);
                return result >= criteriaValue;
            case "MIN_ABOVE_VALUE":
                result = student.getMinScore(subjectName, evaluationNames);
                return result >= criteriaValue;
            default:
                throw new IllegalArgumentException("Tipo de criterio no soportado: " + criteriaType);
        }
    }
}