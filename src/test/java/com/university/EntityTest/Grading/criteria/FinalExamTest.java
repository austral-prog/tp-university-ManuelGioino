package com.university.EntityTest.Grading.criteria;


import com.university.entity.Grading.FinalExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FinalExamTest {
    private FinalExam finalExam;

    @BeforeEach
    public void setUp() {
        finalExam = new FinalExam("Final Exam", "Alice", "Math", "FINAL_PRACTICAL_WORK");
    }

    @Test
    public void testConstructor() {
        assertEquals("Final Exam", finalExam.getName());
        assertEquals("Alice", finalExam.getStudentName());
        assertEquals("Math", finalExam.getSubjectName());
        assertEquals("FINAL_PRACTICAL_WORK", finalExam.getEvaluationType());
        assertNotNull(finalExam.getGrades());
        assertTrue(finalExam.getGrades().isEmpty());
    }

    @Test
    public void testAddGrades() {
        finalExam.addGrades(85);
        finalExam.addGrades(90);
        assertEquals(2, finalExam.getGrades().size());
        assertEquals(85.0, finalExam.getGrades().get(0));
        assertEquals(90.0, finalExam.getGrades().get(1));
    }

    @Test
    public void testGetGradeWithMultipleGrades() {
        finalExam.addGrades(85);
        finalExam.addGrades(90);
        finalExam.addGrades(95);
        assertEquals(270.0, finalExam.getGrade());
    }

    @Test
    public void testGetGradeWithNoGrades() {
        assertEquals(0.0, finalExam.getGrade());
    }

    @Test
    public void testSettersAndGetters() {
        finalExam.setName("Updated Final Exam");
        finalExam.setStudentName("Bob");
        finalExam.setSubjectName("Science");
        finalExam.setEvaluationType("FINAL_EXAM");

        assertEquals("Updated Final Exam", finalExam.getName());
        assertEquals("Bob", finalExam.getStudentName());
        assertEquals("Science", finalExam.getSubjectName());
        assertEquals("FINAL_EXAM", finalExam.getEvaluationType());
    }

    @Test
    public void testSetIdAndGetId() {
        finalExam.setId(10);
        assertEquals(10, finalExam.getId());
    }

    @Test
    public void testToString() {
        finalExam.addGrades(85);
        finalExam.addGrades(90);
        String expected = "Evaluation{id=" + finalExam.getId() +
                ", name='Final Exam', studentName='Alice', subjectName='Math', evaluationType='FINAL_PRACTICAL_WORK', grades=[85.0, 90.0]}";
        assertEquals(expected, finalExam.toString());
    }
}
