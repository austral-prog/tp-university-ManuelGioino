package com.university.University;

import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;

import java.util.*;

public class University {
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Evaluation> evaluations;

    public University() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) { // Evitar duplicados
            this.students.add(student);
        }
    }

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            this.courses.add(course);
        }
    }

    public void addEvaluation(Evaluation evaluation) {
        if (!evaluations.contains(evaluation)) {
            this.evaluations.add(evaluation);
        }
    }

    public Map<String, Integer> getStudentCourseCounts() {
        Map<String, Integer> studentCounts = new HashMap<>();
        for (Student student : students) {
            studentCounts.put(student.getName(), student.getCourseCount());
        }
        return studentCounts;
    }
}
