package com.university.TpProcessorTest.Part3;

import com.university.TpProcessor.Part3.Part3DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.Criterion;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Part3DataCreatorTest {

    private Part3DataCreator part3DataCreator;
    private University university;
    private CriteriaProcessor criteriaProcessor;

    @BeforeEach
    void setUp() {
        part3DataCreator = new Part3DataCreator();
        university = new University();
        criteriaProcessor = new CriteriaProcessor();
    }

    @Test
    void testCreateValidCriterion() {
        // Arrange
        String input = "Physics,AVERAGE_ABOVE_VALUE,5.0,TP Final,Primer Parcial";

        // Act
        part3DataCreator.create(input, university, criteriaProcessor);

        // Assert
        List<Criterion> criteriaList = criteriaProcessor.getCriteriaList();
        assertEquals(1, criteriaList.size());
        Criterion criterion = criteriaList.get(0);
        assertEquals("Physics", criterion.getSubjectName());
        assertNotNull(criterion); // Ensuring Criterion object was added
    }


    @Test
    void testCreateWithNoEvaluationNames() {
        // Arrange
        String input = "Mathematics,MAX_ABOVE_VALUE,8.0";

        // Act
        part3DataCreator.create(input, university, criteriaProcessor);

        // Assert
        List<Criterion> criteriaList = criteriaProcessor.getCriteriaList();
        assertEquals(1, criteriaList.size());
        Criterion criterion = criteriaList.get(0);
        assertEquals("Mathematics", criterion.getSubjectName());
        assertNotNull(criterion); // Ensuring Criterion object was added
    }

    @Test
    void testCreateHandlesMalformedInput() {
        // Arrange
        String malformedInput = "Physics,AVERAGE_ABOVE_VALUE";

        // Act & Assert
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                part3DataCreator.create(malformedInput, university, criteriaProcessor)
        );
        assertNotNull(exception.getMessage());
    }
}
