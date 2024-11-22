package com.university.EntityTest.Grading.criteria;

import com.university.entity.Grading.PracticalWork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PracticalWorkTest {

    private PracticalWork practicalWork;

    @BeforeEach
    public void setUp() {
        practicalWork = new PracticalWork("TP1", "John Doe", "Mathematics", "PRACTICAL_WORK");
    }

    @Test
    public void testGetGradeWithGrades() {
        practicalWork.addGrades(8.0);
        practicalWork.addGrades(9.5);
        double lastGrade = practicalWork.getGrade();
        assertEquals(9.5, lastGrade, 0.01, "The last grade should be returned.");
    }

    @Test
    public void testAttributes() {
        assertEquals("TP1", practicalWork.getName());
        assertEquals("John Doe", practicalWork.getStudentName());
        assertEquals("Mathematics", practicalWork.getSubjectName());
        assertEquals("PRACTICAL_WORK", practicalWork.getEvaluationType());
    }

    @Test
    public void testAddGrades() {
        practicalWork.addGrades(10.0);
        practicalWork.addGrades(7.0);
        assertEquals(2, practicalWork.getGrades().size(), "Grades size should be 2.");
        assertEquals(7.0, practicalWork.getGrades().get(1), 0.01, "The last grade should match.");
    }

    @Test
    public void testToString() {
        practicalWork.addGrades(5.0);
        String expected = "Evaluation{id=" + practicalWork.getId() +
                ", name='TP1', studentName='John Doe', subjectName='Mathematics', evaluationType='PRACTICAL_WORK', grades=[5.0]}";
        assertEquals(expected, practicalWork.toString(), "The toString output should match.");
    }
}
