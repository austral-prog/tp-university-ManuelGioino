package com.university;

public class StudentGradeInfo {
    private String studentName;
    private String subjectName;
    private String evaluationName;
    private double FINAL_PRACTICAL_WORK;
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
        this.FINAL_PRACTICAL_WORK += grade; // Suma todas las notas del examen final
    }

    public void setLastPracticalWorkGrade(double grade) {
        this.lastPracticalWorkGrade = grade; // Guarda la última nota de trabajo práctico
    }

    public void setOralExamGrade(double grade) {
        this.oralExamGrade = grade; // Solo una nota para el examen oral
    }

    public void addWrittenExamGrade(double grade) {
        this.writtenExamSum += grade; // Suma las notas de los exámenes escritos
        this.writtenExamCount++;      // Incrementa el contador para el promedio
    }

    public double calculateFinalGrade() {
        if (FINAL_PRACTICAL_WORK > 0) {
            return FINAL_PRACTICAL_WORK;  // Suma para exámenes finales
        } else if (lastPracticalWorkGrade > 0) {
            return lastPracticalWorkGrade;  // Última nota de trabajos prácticos
        } else if (oralExamGrade > 0) {
            return oralExamGrade;  // Única nota para examen oral
        } else if (writtenExamCount > 0) {
            return writtenExamSum / writtenExamCount;  // Promedio para exámenes escritos
        }
        return 0;  // Retorno por defecto si no hay evaluaciones
    }

    // Getters
    public String getStudentName() {
        return studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public double getFinalGrade() {
        return calculateFinalGrade();
    }
}
