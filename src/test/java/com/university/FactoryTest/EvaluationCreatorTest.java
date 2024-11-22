package com.university.FactoryTest;

import com.university.Factory.EvaluationCreator;
import com.university.University.University;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationCreatorTest {

    private EvaluationCreator evaluationCreator;
    private List<Evaluation> evaluations;
    private University university;

    @BeforeEach
    void setUp() {
        evaluations = new ArrayList<>();
        university = new University();
        evaluationCreator = new EvaluationCreator(evaluations);
    }

    @Test
    void testCreateNewEvaluation() {
        String parts = "John Doe,Math,WRITTEN_EXAM,Exam1,,85";
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, evaluations, university);

        assertNotNull(evaluation, "Evaluation should not be null");
        assertTrue(evaluation instanceof WrittenExam, "Evaluation should be of type WrittenExam");
        assertEquals("Math", evaluation.getSubjectName(), "Subject name should match");
        assertEquals("Exam1", evaluation.getName(), "Evaluation name should match");
        assertEquals(85.0, evaluation.getGrades().get(0), "Grade should match");
        assertEquals(1, university.getEvaluations().size(), "University should contain one evaluation");
    }

    @Test
    void testFindExistingEvaluation() {
        String parts = "John Doe,Math,WRITTEN_EXAM,Exam1,,85";
        Evaluation firstEval = evaluationCreator.getOrCreate(parts, evaluations, university);

        Evaluation secondEval = evaluationCreator.getOrCreate(parts, evaluations, university);

        assertSame(firstEval, secondEval, "Should return the same evaluation object");
        assertEquals(1, university.getEvaluations().size(), "No duplicate evaluations should be created");
    }

    @Test
    void testCreateNewStudent() {
        String parts = "Jane Smith,Physics,ORAL_EXAM,Oral1,,90";
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, evaluations, university);

        assertNotNull(evaluation, "Evaluation should not be null");
        Student student = university.getStudents().get(0);
        assertEquals("Jane Smith", student.getName(), "Student name should match");
        assertEquals(1, university.getStudents().size(), "Only one student should exist");
    }

    @Test
    void testFindExistingStudent() {
        Student existingStudent = new Student("Jane Smith", "jane@example.com");
        university.addStudent(existingStudent);

        String parts = "Jane Smith,Physics,ORAL_EXAM,Oral1,,90";
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, evaluations, university);

        assertNotNull(evaluation, "Evaluation should not be null");
        assertEquals(1, university.getStudents().size(), "No duplicate students should be created");
    }

    @Test
    void testCreateDifferentEvaluationTypes() {
        String writtenExamParts = "John Doe,Math,WRITTEN_EXAM,Exam1,,85";
        String oralExamParts = "Jane Smith,Physics,ORAL_EXAM,Oral1,,90";
        String practicalWorkParts = "Alice Brown,Chemistry,PRACTICAL_WORK,Practical1,,95";
        String finalExamParts = "Bob White,History,FINAL_PRACTICAL_WORK,Final1,,88";

        Evaluation writtenExam = evaluationCreator.getOrCreate(writtenExamParts, evaluations, university);
        Evaluation oralExam = evaluationCreator.getOrCreate(oralExamParts, evaluations, university);
        Evaluation practicalWork = evaluationCreator.getOrCreate(practicalWorkParts, evaluations, university);
        Evaluation finalExam = evaluationCreator.getOrCreate(finalExamParts, evaluations, university);

        assertTrue(writtenExam instanceof WrittenExam, "Should create WrittenExam");
        assertTrue(oralExam instanceof OralExam, "Should create OralExam");
        assertTrue(practicalWork instanceof PracticalWork, "Should create PracticalWork");
        assertTrue(finalExam instanceof FinalExam, "Should create FinalExam");
    }

    @Test
    void testCreateEvaluationWithInvalidType() {
        String invalidTypeParts = "John Doe,Math,UNKNOWN_TYPE,Exam1,,85";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluationCreator.getOrCreate(invalidTypeParts, evaluations, university);
        });

        assertEquals("Unknown evaluation type: UNKNOWN_TYPE", exception.getMessage(), "Exception message should match");
    }

    @Test
    void testHandleInvalidGradeFormat() {
        String invalidGradeParts = "John Doe,Math,WRITTEN_EXAM,Exam1,,invalidGrade";

        assertThrows(NumberFormatException.class, () -> {
            evaluationCreator.getOrCreate(invalidGradeParts, evaluations, university);
        }, "Should throw NumberFormatException for invalid grade format");
    }
}
