package com.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationProcessor {

    public Map<String, StudentGradeInfo> processEvaluations(List<String[]> data) {
        Map<String, StudentGradeInfo> studentGrades = new HashMap<>();

        for (String[] row : data) {
            String studentName = row[0].trim();
            String subjectName = row[1].trim();
            String evaluationType = row[2].trim();
            String evaluationName = row[3].trim();
            double grade = Double.parseDouble(row[5].trim());

            // Clave única para cada estudiante y materia
            String key = studentName + "_" + subjectName;
            StudentGradeInfo gradeInfo = studentGrades.getOrDefault(key, new StudentGradeInfo(studentName, subjectName, evaluationName));

            // Procesar cada tipo de evaluación según los criterios de calificación
            switch (evaluationType) {
                case "FINAL_EXAM":
                    gradeInfo.addFinalExamGrade(grade);
                    break;
                case "PRACTICAL_WORK":
                    gradeInfo.setLastPracticalWorkGrade(grade);
                    break;
                case "ORAL_EXAM":
                    gradeInfo.setOralExamGrade(grade);
                    break;
                case "WRITTEN_EXAM":
                    gradeInfo.addWrittenExamGrade(grade);
                    break;
            }

            // Almacenar el objeto actualizado
            studentGrades.put(key, gradeInfo);
        }

        return studentGrades;
    }
}
