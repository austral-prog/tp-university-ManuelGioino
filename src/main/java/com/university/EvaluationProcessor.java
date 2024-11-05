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

            // Crear una clave única que incluya studentName, subjectName, evaluationType y evaluationName
            String key = studentName + "_" + subjectName + "_" + evaluationType + "_" + evaluationName;

            // Obtener o crear una nueva instancia de StudentGradeInfo para cada evaluación
            StudentGradeInfo gradeInfo = studentGrades.getOrDefault(key, new StudentGradeInfo(studentName, subjectName, evaluationName));

            // Agregar la nota según el tipo de evaluación
            switch (evaluationType) {
                case "FINAL_PRACTICAL_WORK":
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

            // Guardar la evaluación en el mapa con la clave completa
            studentGrades.put(key, gradeInfo);
        }

        return studentGrades;
    }
}
