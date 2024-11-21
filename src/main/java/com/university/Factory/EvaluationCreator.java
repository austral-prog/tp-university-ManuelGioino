package com.university.Factory;

import com.university.University.University;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.*;

import java.util.List;

public class EvaluationCreator implements EntityCreator<Evaluation> {
    private List<Evaluation> evaluations;

    public EvaluationCreator(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Evaluation getOrCreate(String parts, List<Evaluation> evaluations, University university) {
        String[] params = parts.split(",");
        String studentName = params[0].trim();
        String subjectName = params[1].trim();
        String evaluationType = params[2].trim();
        String evaluationName = params[3].trim();
        double grade = Double.parseDouble(params[5].trim());

        // Busca o crea el estudiante
        Student student = findOrCreateStudent(studentName, university);

        // Busca o crea la evaluación
        Evaluation evaluation = findEvaluation(subjectName, evaluationName, studentName, evaluationType, university);

        if (evaluation == null) {
            evaluation = createEvaluation(subjectName, evaluationName, studentName, evaluationType);
            university.getEvaluations().add(evaluation);
            student.addEvaluation(evaluation);
        }

        evaluation.addGrades(grade);
        return evaluation;
    }

    private Student findOrCreateStudent(String studentName, University university) {
        for (Student student : university.getStudents()) {
            if (student.getName().equalsIgnoreCase(studentName.trim())) {
                return student;
            }
        }
        // Si no existe, creamos y agregamos el estudiante
        Student newStudent = new Student(studentName, ""); // Email vacío por defecto
        university.addStudent(newStudent);
        return newStudent;
    }

    private Evaluation findEvaluation(String subjectName, String evaluationName, String studentName, String evaluationType, University university) {
        for (Evaluation e : university.getEvaluations()) {
            if (e.getSubjectName().equals(subjectName) &&
                    e.getName().equals(evaluationName) &&
                    e.getStudentName().equals(studentName) &&
                    e.getEvaluationType().equals(evaluationType)) {
                return e;
            }
        }
        return null;
    }

    public static Evaluation createEvaluation(String subjectName, String evaluationName, String studentName, String evaluationType) {
        switch (evaluationType) {
            case "WRITTEN_EXAM":
                return new WrittenExam(evaluationName, studentName, subjectName, evaluationType);
            case "PRACTICAL_WORK":
                return new PracticalWork(evaluationName, studentName, subjectName, evaluationType);
            case "ORAL_EXAM":
                return new OralExam(evaluationName, studentName, subjectName, evaluationType);
            case "FINAL_PRACTICAL_WORK":
                return new FinalExam(evaluationName, studentName, subjectName, evaluationType);
            default:
                throw new IllegalArgumentException("Unknown evaluation type: " + evaluationType);
        }
    }
}
