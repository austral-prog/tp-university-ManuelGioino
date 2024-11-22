package com.university.EntityTest.Grading.criteria;

import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;
import com.university.entity.Grading.WrittenExam;
import com.university.entity.Grading.criteria.Criterion;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriterionTest {

    @Test
    void testEvaluateWithAverageAboveValue() {
        // Arrange
        Student student = new Student("Alice", "alice@example.com");
        Evaluation evaluation1 = new WrittenExam("Primer Parcial", "Alice", "Math", "WRITTEN_EXAM");
        evaluation1.addGrades(8.0);
        evaluation1.addGrades(9.0);
        student.addEvaluation(evaluation1);

        List<String> evaluationNames = Arrays.asList("Primer Parcial");
        Criterion criterion = new Criterion("Math", "AVERAGE_ABOVE_VALUE", 7.5, evaluationNames);

        // Act
        boolean result = criterion.evaluate(student);

        // Assert
        assertTrue(result, "Expected student to pass the criterion.");
    }

    @Test
    void testEvaluateWithMaxAboveValue() {
        // Arrange
        Student student = new Student("Bob", "bob@example.com");
        Evaluation evaluation1 = new WrittenExam("Segundo Parcial", "Bob", "Physics", "WRITTEN_EXAM");
        evaluation1.addGrades(6.0);
        evaluation1.addGrades(7.0);
        student.addEvaluation(evaluation1);

        List<String> evaluationNames = Arrays.asList("Segundo Parcial");
        Criterion criterion = new Criterion("Physics", "MAX_ABOVE_VALUE", 6.5, evaluationNames);

        // Act
        boolean result = criterion.evaluate(student);

        // Assert
        assertTrue(result, "Expected student to pass the criterion.");
    }

    @Test
    void testEvaluateWithMinAboveValue() {
        // Arrange
        Student student = new Student("Charlie", "charlie@example.com");
        Evaluation evaluation1 = new WrittenExam("Examen Final", "Charlie", "Chemistry", "WRITTEN_EXAM");
        evaluation1.addGrades(5.0);
        evaluation1.addGrades(4.5);
        student.addEvaluation(evaluation1);

        List<String> evaluationNames = Arrays.asList("Examen Final");
        Criterion criterion = new Criterion("Chemistry", "MIN_ABOVE_VALUE", 4.0, evaluationNames);

        // Act
        boolean result = criterion.evaluate(student);

        // Assert
        assertTrue(result, "Expected student to pass the criterion.");
    }

    @Test
    void testEvaluateWithUnsupportedCriteriaType() {
        // Arrange
        Student student = new Student("Daisy", "daisy@example.com");
        List<String> evaluationNames = Arrays.asList("Primer Parcial");
        Criterion criterion = new Criterion("Math", "UNSUPPORTED_CRITERIA", 7.5, evaluationNames);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> criterion.evaluate(student));
        assertEquals("Tipo de criterio no soportado: UNSUPPORTED_CRITERIA", exception.getMessage());
    }
}
