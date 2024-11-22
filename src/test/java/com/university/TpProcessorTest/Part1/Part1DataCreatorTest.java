package com.university.TpProcessorTest.Part1;

import com.university.TpProcessor.Part1.Part1DataCreator;
import com.university.University.University;
import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Part1DataCreatorTest {

    private Part1DataCreator part1DataCreator;
    private University university;

    @BeforeEach
    void setUp() {
        university = new University();
        part1DataCreator = new Part1DataCreator();
    }

    @Test
    void testCreateNewStudentAndCourse() {
        String parts = "101,Mathematics,John Doe,john.doe@example.com";

        part1DataCreator.create(parts, university, new CriteriaProcessor());

        // Validate Student
        List<Student> students = university.getStudents();
        assertEquals(1, students.size(), "One student should be created");
        Student student = students.get(0);
        assertEquals("John Doe", student.getName(), "Student name should match");
        assertEquals("john.doe@example.com", student.getEmail(), "Student email should match");

        // Validate Course
        List<Course> courses = university.getCourses();
        assertEquals(1, courses.size(), "One course should be created");
        Course course = courses.get(0);
        assertEquals("Mathematics", course.getSubject(), "Course subject should match");
        assertEquals(101, course.getClassroom(), "Course classroom should match");

        // Validate Relationships
        assertTrue(student.getCourses().contains(course), "Student should be enrolled in the course");
        assertTrue(course.getStudents().contains(student), "Course should have the student enrolled");
    }

    @Test
    void testAddStudentToExistingCourse() {
        String courseParts = "101,Mathematics,John Doe,john.doe@example.com";
        String newStudentParts = "101,Mathematics,Jane Smith,jane.smith@example.com";

        part1DataCreator.create(courseParts, university, new CriteriaProcessor());
        part1DataCreator.create(newStudentParts, university, new CriteriaProcessor());

        // Validate Students
        List<Student> students = university.getStudents();
        assertEquals(2, students.size(), "Two students should be created");

        // Validate Course
        List<Course> courses = university.getCourses();
        assertEquals(1, courses.size(), "One course should be created");
        Course course = courses.get(0);
        assertEquals(2, course.getStudents().size(), "Course should have two students enrolled");
    }

    @Test
    void testReuseExistingStudent() {
        String parts1 = "101,Mathematics,John Doe,john.doe@example.com";
        String parts2 = "102,Physics,John Doe,john.doe@example.com";

        part1DataCreator.create(parts1, university, new CriteriaProcessor());
        part1DataCreator.create(parts2, university, new CriteriaProcessor());

        // Validate Students
        List<Student> students = university.getStudents();
        assertEquals(1, students.size(), "Only one student should be created");

        // Validate Courses
        List<Course> courses = university.getCourses();
        assertEquals(2, courses.size(), "Two courses should be created");
        Student student = students.get(0);
        assertEquals(2, student.getCourses().size(), "Student should be enrolled in two courses");
    }

    @Test
    void testInvalidPartsThrowsException() {
        String invalidParts = "101,Mathematics"; // Missing student info

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            part1DataCreator.create(invalidParts, university, new CriteriaProcessor());
        });

        assertEquals("Index 2 out of bounds for length 2", exception.getMessage(), "Exception message should match");
    }
}
