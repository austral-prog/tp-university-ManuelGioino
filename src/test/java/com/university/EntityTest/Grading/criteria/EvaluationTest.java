package com.university.EntityTest.Grading.criteria;


import com.university.entity.Grading.Evaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationTest {
    private Evaluation evaluation;

    // Clase concreta para test
    private static class TestEvaluation extends Evaluation {
        public TestEvaluation(String name, String studentName, String subjectName, String evaluationType) {
            super(name, studentName, subjectName, evaluationType);
        }

        @Override
        public double getGrade() {
            return getGrades().stream().mapToDouble(Double::doubleValue).average().orElse(0);
        }
    }

    @BeforeEach
    public void setUp() {
        evaluation = new TestEvaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM");
    }

    @Test
    public void testConstructor() {
        assertEquals("Midterm", evaluation.getName());
        assertEquals("Alice", evaluation.getStudentName());
        assertEquals("Math", evaluation.getSubjectName());
        assertEquals("WRITTEN_EXAM", evaluation.getEvaluationType());
        assertNotNull(evaluation.getGrades());
        assertTrue(evaluation.getGrades().isEmpty());
    }

    @Test
    public void testSettersAndGetters() {
        evaluation.setName("Final Exam");
        evaluation.setStudentName("Bob");
        evaluation.setSubjectName("Science");
        evaluation.setEvaluationType("FINAL_EXAM");

        assertEquals("Final Exam", evaluation.getName());
        assertEquals("Bob", evaluation.getStudentName());
        assertEquals("Science", evaluation.getSubjectName());
        assertEquals("FINAL_EXAM", evaluation.getEvaluationType());
    }

    @Test
    public void testAddGrades() {
        evaluation.addGrades(85);
        evaluation.addGrades(90);

        List<Double> grades = evaluation.getGrades();
        assertEquals(2, grades.size());
        assertEquals(85.0, grades.get(0));
        assertEquals(90.0, grades.get(1));
    }

    @Test
    public void testGetGrade() {
        evaluation.addGrades(80);
        evaluation.addGrades(90);
        assertEquals(85.0, evaluation.getGrade());
    }

    @Test
    public void testSetIdAndGetId() {
        evaluation.setId(10);
        assertEquals(10, evaluation.getId());
    }

    @Test
    public void testToString() {
        evaluation.addGrades(80);
        evaluation.addGrades(90);
        String expected = "Evaluation{id=" + evaluation.getId() +
                ", name='Midterm', studentName='Alice', subjectName='Math', evaluationType='WRITTEN_EXAM', grades=[80.0, 90.0]}";
        assertEquals(expected, evaluation.toString());
    }
}
