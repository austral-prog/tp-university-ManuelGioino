package com.university.FactoryTest;

import com.university.Factory.CourseCreator;
import com.university.University.University;
import com.university.entity.Classroom.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseCreatorTest {

    private CourseCreator courseCreator;
    private List<Course> courses;
    private University university;

    @BeforeEach
    void setUp() {
        courses = new ArrayList<>();
        university = new University();
        courseCreator = new CourseCreator(courses);
    }

    @Test
    void testGetOrCreateCreatesNewCourse() {
        String parts = "101,Mathematics";
        Course createdCourse = courseCreator.getOrCreate(parts, courses, university);

        assertNotNull(createdCourse, "Course should not be null");
        assertEquals(101, createdCourse.getClassroom(), "Classroom number should match");
        assertEquals("Mathematics", createdCourse.getSubject(), "Subject should match");
        assertTrue(university.getCourses().contains(createdCourse), "Course should be added to the university");
    }

    @Test
    void testGetOrCreateFindsExistingCourse() {
        Course existingCourse = new Course(101, "Mathematics");
        university.getCourses().add(existingCourse);

        String parts = "101,Mathematics";
        Course retrievedCourse = courseCreator.getOrCreate(parts, courses, university);

        assertSame(existingCourse, retrievedCourse, "Should return the existing course");
        assertEquals(1, university.getCourses().size(), "No new course should be added");
    }

    @Test
    void testGetOrCreateHandlesMultipleCourses() {
        university.getCourses().add(new Course(102, "Physics"));
        university.getCourses().add(new Course(103, "Chemistry"));

        String parts = "104,Biology";
        Course newCourse = courseCreator.getOrCreate(parts, courses, university);

        assertNotNull(newCourse, "New course should be created");
        assertEquals("Biology", newCourse.getSubject(), "Subject should match");
        assertEquals(104, newCourse.getClassroom(), "Classroom should match");
        assertEquals(3, university.getCourses().size(), "Total courses should be 3");
    }

    @Test
    void testGetOrCreateHandlesInvalidInput() {
        String parts = "invalid,Biology";
        assertThrows(NumberFormatException.class, () -> courseCreator.getOrCreate(parts, courses, university),
                "Should throw NumberFormatException for invalid classroom");
    }

    @Test
    void testFindCourseBySubjectReturnsCourse() {
        Course existingCourse = new Course(101, "Mathematics");
        university.getCourses().add(existingCourse);

        Course foundCourse = courseCreator.getOrCreate("101,Mathematics", courses, university);
        assertNotNull(foundCourse, "Found course should not be null");
        assertEquals("Mathematics", foundCourse.getSubject(), "Subject should match");
    }

    @Test
    void testFindCourseBySubjectReturnsNull() {
        Course foundCourse = courseCreator.getOrCreate("201,Physics", courses, university);
        assertNotNull(foundCourse, "New course should be created if not found");
        assertEquals("Physics", foundCourse.getSubject(), "New course subject should match");
    }
}
