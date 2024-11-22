package com.university.EntityTest.Classroom;

import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("Alice", "alice@example.com");
    }

    @Test
    public void testConstructor() {
        assertEquals("Alice", student.getName());
        assertEquals("alice@example.com", student.getEmail());
        assertNotNull(student.getCourses());
        assertTrue(student.getCourses().isEmpty());
    }

    @Test
    public void testAddToCourse() {
        Course course = new Course(101, "Mathematics");
        student.addToCourse(course);

        HashSet<Course> courses = student.getCourses();
        assertTrue(courses.contains(course));
        assertEquals(1, courses.size());
    }

    @Test
    public void testGetCourseCount() {
        Course course1 = new Course(101, "Mathematics");
        Course course2 = new Course(102, "Physics");

        student.addToCourse(course1);
        student.addToCourse(course2);

        assertEquals(2, student.getCourseCount());
    }

    @Test
    public void testSetEmail() {
        student.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", student.getEmail());
    }


    @Test
    public void testAddEvaluation() {
        Evaluation evaluation = new Evaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM") {
            @Override
            public double getGrade() {
                return 0;
            }
        };

        student.addEvaluation(evaluation);
        assertTrue(student.getEvaluations().contains(evaluation));
        assertEquals(1, student.getEvaluations().size());
    }

    @Test
    public void testGetGradesOfEval() {
        Evaluation evaluation = new Evaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM") {
            @Override
            public double getGrade() {
                return 0;
            }
        };
        evaluation.addGrades(85);
        evaluation.addGrades(90);
        student.addEvaluation(evaluation);

        assertEquals(2, student.getGradesOfEval("Math", List.of("Midterm")).size());
        assertEquals(85.0, student.getGradesOfEval("Math", List.of("Midterm")).get(0));
    }

    @Test
    public void testCalculateAverage() {
        Evaluation evaluation = new Evaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM") {
            @Override
            public double getGrade() {
                return 0;
            }
        };
        evaluation.addGrades(80);
        evaluation.addGrades(90);
        student.addEvaluation(evaluation);

        assertEquals(85.0, student.calculateAverage("Math", List.of("Midterm")));
    }

    @Test
    public void testGetMaxScore() {
        Evaluation evaluation = new Evaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM") {
            @Override
            public double getGrade() {
                return 0;
            }
        };
        evaluation.addGrades(70);
        evaluation.addGrades(95);
        student.addEvaluation(evaluation);

        assertEquals(95.0, student.getMaxScore("Math", List.of("Midterm")));
    }

    @Test
    public void testGetMinScore() {
        Evaluation evaluation = new Evaluation("Midterm", "Alice", "Math", "WRITTEN_EXAM") {
            @Override
            public double getGrade() {
                return 0;
            }
        };
        evaluation.addGrades(70);
        evaluation.addGrades(95);
        student.addEvaluation(evaluation);

        assertEquals(70.0, student.getMinScore("Math", List.of("Midterm")));
    }
}
