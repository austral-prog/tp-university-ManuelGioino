package com.university.TpProcessor.Part1;

import com.university.Factory.CourseCreator;
import com.university.Factory.StudentCreator;
import com.university.TpProcessor.Common.DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;

public class Part1DataCreator implements DataCreator {
    @Override
    public void create(String parts, University university, CriteriaProcessor criteriaProcessor) {
        StudentCreator studentCreator = new StudentCreator(university.getStudents());
        CourseCreator courseCreator = new CourseCreator(university.getCourses());
        Student student = studentCreator.getOrCreate(parts, university.getStudents(), university);
        Course course = courseCreator.getOrCreate(parts, university.getCourses(), university);
        student.addToCourse(course);
        course.addStudent(student);
    }
}