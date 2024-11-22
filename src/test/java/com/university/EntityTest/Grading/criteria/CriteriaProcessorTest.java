package com.university.EntityTest.Grading.criteria;

import com.university.University.University;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;
import com.university.entity.Grading.WrittenExam;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.entity.Grading.criteria.Criterion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CriteriaProcessorTest {

    private CriteriaProcessor criteriaProcessor;
    private University university;

    @BeforeEach
    public void setUp() {
        criteriaProcessor = new CriteriaProcessor();
        university = new University();

        // Add criteria
        criteriaProcessor.addCriteria(new Criterion("Math", "AVERAGE_ABOVE_VALUE", 5.0, List.of("Exam1", "Exam2")));
        criteriaProcessor.addCriteria(new Criterion("History", "MAX_ABOVE_VALUE", 7.0, List.of("Final Exam")));
    }

    @Test
    public void testAddCriteria() {
        assertEquals(2, criteriaProcessor.getCriteriaList().size(), "Should have 2 criteria added.");
    }

    @Test
    public void testEvaluateStudentsWithPassingGrades() {
        // Add students and evaluations
        Student student = new Student("Alice", "alice@example.com");
        Evaluation mathExam1 = new WrittenExam("Exam1", "Alice", "Math", "WRITTEN_EXAM");
        Evaluation mathExam2 = new WrittenExam("Exam2", "Alice", "Math", "WRITTEN_EXAM");
        mathExam1.addGrades(6.0);
        mathExam2.addGrades(7.0);
        student.addEvaluation(mathExam1);
        student.addEvaluation(mathExam2);
        university.addStudent(student);

        // Evaluate
        List<String> results = criteriaProcessor.evaluateStudents(university);

        assertEquals(1, results.size(), "Should evaluate one subject.");
        assertTrue(results.get(0).contains("PASSED"), "Student should pass Math.");
    }

    @Test
    public void testEvaluateStudentsWithFailingGrades() {
        // Add students and evaluations
        Student student = new Student("Bob", "bob@example.com");
        Evaluation historyExam = new WrittenExam("Final Exam", "Bob", "History", "WRITTEN_EXAM");
        historyExam.addGrades(6.0);
        student.addEvaluation(historyExam);
        university.addStudent(student);

        // Evaluate
        List<String> results = criteriaProcessor.evaluateStudents(university);

        assertEquals(1, results.size(), "Should evaluate one subject.");
        assertTrue(results.get(0).contains("FAILED"), "Student should fail History.");
    }

    @Test
    public void testEvaluateStudentsWithNoCriteria() {
        // Add a student with evaluations
        Student student = new Student("Charlie", "charlie@example.com");
        Evaluation scienceExam = new WrittenExam("Midterm", "Charlie", "Science", "WRITTEN_EXAM");
        scienceExam.addGrades(8.0);
        student.addEvaluation(scienceExam);
        university.addStudent(student);

        // Evaluate
        List<String> results = criteriaProcessor.evaluateStudents(university);

        assertEquals(1, results.size(), "Should evaluate one subject.");
        assertTrue(results.get(0).contains("FAILED"), "Student should fail because there is no criterion for Science.");
    }

    @Test
    public void testEvaluateStudentsAlphabeticalOrder() {
        // Add students and evaluations
        Student student1 = new Student("Dave", "dave@example.com");
        Student student2 = new Student("Alice", "alice@example.com");

        Evaluation mathExam1 = new WrittenExam("Exam1", "Dave", "Math", "WRITTEN_EXAM");
        mathExam1.addGrades(6.0);
        Evaluation historyExam = new WrittenExam("Final Exam", "Alice", "History", "WRITTEN_EXAM");
        historyExam.addGrades(8.0);

        student1.addEvaluation(mathExam1);
        student2.addEvaluation(historyExam);

        university.addStudent(student1);
        university.addStudent(student2);

        // Evaluate
        List<String> results = criteriaProcessor.evaluateStudents(university);

        assertEquals(2, results.size(), "Should evaluate two students.");
        assertEquals("Alice,History,PASSED", results.get(0), "Alice's result should be first in alphabetical order.");
        assertEquals("Dave,Math,PASSED", results.get(1), "Dave's result should be second.");
    }

    @Test
    public void testEvaluateStudentsNoEvaluations() {
        // Add a student with no evaluations
        Student student = new Student("Eve", "eve@example.com");
        university.addStudent(student);

        // Evaluate
        List<String> results = criteriaProcessor.evaluateStudents(university);

        assertEquals(0, results.size(), "No evaluations mean no results.");
    }
}
