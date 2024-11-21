package com.university.entity.Classroom;

import com.university.entity.Entity;
import com.university.entity.Grading.Evaluation;

import java.util.*;

public class Student implements Entity {
    private static int idCounter = 0;
    private int id;
    private String name;
    private HashSet<Course> courses;
    private String email;
    private List<Evaluation> evaluations;

    public Student(){}

    public Student(String name, String email) {
        this.name = name;
        this.id = ++idCounter;
        this.courses = new HashSet<>();
        this.email = email;
        this.evaluations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Course> getCourses() {
        return courses;
    }

    public void addToCourse(Course course){
        if(!courses.contains(course)){
            this.courses.add(course);
        }
    }

    public int getCourseCount() {
        return courses.size();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addEvaluation(Evaluation evaluation) {
        if (!evaluations.contains(evaluation)) {
            evaluations.add(evaluation);
        }
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public List<Double> getGradesOfEval(String subject, List<String> evaluationNames) {
        List<Double> grades = new ArrayList<>();
        for (Evaluation eval : evaluations) {
            if (eval.getSubjectName().equals(subject) && evaluationNames.contains(eval.getName())) {
                grades.addAll(eval.getGrades());
            }
        }
        return grades;
    }

    public double calculateAverage(String subject, List<String> evaluationNames) {
        List<Double> grades = getGradesOfEval(subject, evaluationNames);
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }


    public double getMaxScore(String subject, List<String> evaluationNames) {
        List<Double> grades = getGradesOfEval(subject, evaluationNames);
        if (grades.isEmpty()) {
            return 0.0;
        }
        double max = grades.get(0);
        for (double grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }


    public double getMinScore(String subject, List<String> evaluationNames) {
        List<Double> grades = getGradesOfEval(subject, evaluationNames);
        if (grades.isEmpty()) {
            return 0.0;
        }
        double min = grades.get(0);
        for (double grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", email='" + email +
                '}';
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}