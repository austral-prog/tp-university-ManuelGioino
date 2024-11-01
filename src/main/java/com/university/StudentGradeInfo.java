package com.university;

public class StudentGradeInfo {
    private String studentName;
    private String subjectName;
    private String evaluationName;
    private double finalExamSum;
    private double lastPracticalWorkGrade;
    private double oralExamGrade;
    private double writtenExamSum;
    private int writtenExamCount;

    public StudentGradeInfo(String studentName, String subjectName, String evaluationName) {
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.evaluationName = evaluationName;
    }

    public void addFinalExamGrade(double grade) {
        this.finalExamSum += grade;
    }

    public void setLastPracticalWorkGrade(double grade) {
        this.lastPracticalWorkGrade = grade;
    }

    public void setOralExamGrade(double grade) {
        this.oralExamGrade = grade;
    }

    public void addWrittenExamGrade(double grade) {
        this.writtenExamSum += grade;
        this.writtenExamCount++;
    }

    public double calculateFinalGrade() {
        if (finalExamSum > 0) {
            return finalExamSum;
        } else if (lastPracticalWorkGrade > 0) {
            return lastPracticalWorkGrade;
        } else if (oralExamGrade > 0) {
            return oralExamGrade;
        } else if (writtenExamCount > 0) {
            return writtenExamSum / writtenExamCount;
        }
        return 0;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getEvaluationName() {
        return evaluationName;  //obtener el nombre de la evaluación
    }

    public double getFinalGrade() {
        return calculateFinalGrade();
    }
}
