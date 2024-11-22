package com.university.TpProcessorTest.Part2;

import com.university.TpProcessor.Part2.Part2DataCreator;
import com.university.University.University;
import com.university.entity.Grading.Evaluation;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Part2DataCreatorTest {

    private Part2DataCreator part2DataCreator;
    private University university;
    private CriteriaProcessor criteriaProcessor;

    @BeforeEach
    void setUp() {
        part2DataCreator = new Part2DataCreator();
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
    }

    @Test
    void testCreateSingleEvaluation() {
        // Input data
        String input = "John Doe,Math,WRITTEN_EXAM,Primer Parcial,Ej1,9";

        // Execute
        part2DataCreator.create(input, university, criteriaProcessor);

        // Verify
        List<Evaluation> evaluations = university.getEvaluations();
        assertEquals(1, evaluations.size(), "There should be one evaluation");

        Evaluation evaluation = evaluations.get(0);
        assertEquals("John Doe", evaluation.getStudentName());
        assertEquals("Math", evaluation.getSubjectName());
        assertEquals("Primer Parcial", evaluation.getName());
        assertEquals("WRITTEN_EXAM", evaluation.getEvaluationType());
        assertEquals(9, evaluation.getGrades().get(0));
    }

    @Test
    void testCreateMultipleEvaluations() {
        // Input data
        String input1 = "Alice,Physics,ORAL_EXAM,Examen Oral,Ej1,8";
        String input2 = "Bob,Chemistry,FINAL_PRACTICAL_WORK,TP Final,Ej3,7";

        // Execute
        part2DataCreator.create(input1, university, criteriaProcessor);
        part2DataCreator.create(input2, university, criteriaProcessor);

        // Verify
        List<Evaluation> evaluations = university.getEvaluations();
        assertEquals(2, evaluations.size());

        Evaluation eval1 = evaluations.get(0);
        Evaluation eval2 = evaluations.get(1);

        assertEquals("Alice", eval1.getStudentName());
        assertEquals("Physics", eval1.getSubjectName());
        assertEquals("Bob", eval2.getStudentName());
        assertEquals("Chemistry", eval2.getSubjectName());
    }

    @Test
    void testCreateInvalidGrade() {
        // Input data with invalid grade
        String input = "Charlie,History,WRITTEN_EXAM,Primer Parcial,Ej1,invalid";

        // Verify exception
        assertThrows(NumberFormatException.class, () -> part2DataCreator.create(input, university, criteriaProcessor));
    }
}
