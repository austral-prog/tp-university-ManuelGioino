package com.university.EntityTest.Grading.criteria;


import com.university.entity.Grading.OralExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OralExamTest {
    private OralExam oralExam;

    @BeforeEach
    public void setUp() {
        oralExam = new OralExam("Oral Exam", "Alice", "History", "ORAL_EXAM");
    }

    @Test
    public void testConstructor() {
        assertEquals("Oral Exam", oralExam.getName());
        assertEquals("Alice", oralExam.getStudentName());
        assertEquals("History", oralExam.getSubjectName());
        assertEquals("ORAL_EXAM", oralExam.getEvaluationType());
        assertNotNull(oralExam.getGrades());
        assertTrue(oralExam.getGrades().isEmpty());
    }

    @Test
    public void testAddGrades() {
        oralExam.addGrades(9.5);
        assertEquals(1, oralExam.getGrades().size());
        assertEquals(9.5, oralExam.getGrades().get(0));
    }

    @Test
    public void testGetGrade() {
        oralExam.addGrades(8.0);
        oralExam.addGrades(9.5); // Aunque hay varias calificaciones, debe devolver la primera
        assertEquals(8.0, oralExam.getGrade());
    }


    @Test
    public void testSettersAndGetters() {
        oralExam.setName("Updated Oral Exam");
        oralExam.setStudentName("Bob");
        oralExam.setSubjectName("Science");
        oralExam.setEvaluationType("ORAL_FINAL");

        assertEquals("Updated Oral Exam", oralExam.getName());
        assertEquals("Bob", oralExam.getStudentName());
        assertEquals("Science", oralExam.getSubjectName());
        assertEquals("ORAL_FINAL", oralExam.getEvaluationType());
    }

    @Test
    public void testSetIdAndGetId() {
        oralExam.setId(20);
        assertEquals(20, oralExam.getId());
    }

    @Test
    public void testToString() {
        oralExam.addGrades(9.0);
        String expected = "Evaluation{id=" + oralExam.getId() +
                ", name='Oral Exam', studentName='Alice', subjectName='History', evaluationType='ORAL_EXAM', grades=[9.0]}";
        assertEquals(expected, oralExam.toString());
    }
}
