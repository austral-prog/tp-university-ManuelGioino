package com.university.EntityTest.Grading.criteria;


import com.university.entity.Grading.WrittenExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WrittenExamTest {

    private WrittenExam writtenExam;

    @BeforeEach
    public void setUp() {
        writtenExam = new WrittenExam("Final Exam", "Jane Doe", "Physics", "WRITTEN_EXAM");
    }

    @Test
    public void testGetGradeWithGrades() {
        writtenExam.addGrades(8.0);
        writtenExam.addGrades(9.0);
        writtenExam.addGrades(7.0);
        double averageGrade = writtenExam.getGrade();
        assertEquals(8.0, averageGrade, 0.01, "The average grade should be calculated correctly.");
    }



    @Test
    public void testAttributes() {
        assertEquals("Final Exam", writtenExam.getName());
        assertEquals("Jane Doe", writtenExam.getStudentName());
        assertEquals("Physics", writtenExam.getSubjectName());
        assertEquals("WRITTEN_EXAM", writtenExam.getEvaluationType());
    }

    @Test
    public void testAddGrades() {
        writtenExam.addGrades(10.0);
        writtenExam.addGrades(8.0);
        assertEquals(2, writtenExam.getGrades().size(), "Grades size should be 2.");
        assertEquals(8.0, writtenExam.getGrades().get(1), 0.01, "The last grade should match.");
    }

    @Test
    public void testToString() {
        writtenExam.addGrades(5.0);
        String expected = "Evaluation{id=" + writtenExam.getId() +
                ", name='Final Exam', studentName='Jane Doe', subjectName='Physics', evaluationType='WRITTEN_EXAM', grades=[5.0]}";
        assertEquals(expected, writtenExam.toString(), "The toString output should match.");
    }
}
