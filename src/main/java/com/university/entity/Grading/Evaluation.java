package com.university.entity.Grading;
import com.university.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Evaluation implements Entity {

    private static int idCounter = 0;
    private int id;
    private String name;
    private String studentName;
    private String subjectName;
    private String evaluationType;
    private List<Double> grades;

    public Evaluation(String name, String studentName, String subjectName, String evaluationType){
        this.id = ++idCounter;
        this.name = name;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.evaluationType = evaluationType;
        this.grades = new ArrayList<>();
    }
    public int getId(){return this.id;}

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){this.name = name;}

    public abstract double getGrade();

    public String getStudentName() {
        return studentName;
    }

    public void setEvaluationType(String evaluationType) {
        this.evaluationType = evaluationType;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSubjectName(String subjectName) {this.subjectName = subjectName;}

    public String getSubjectName() {
        return subjectName;
    }

    public List<Double> getGrades() {
        return this.grades;
    }

    public String getEvaluationType(){return this.evaluationType;}

    public void addGrades(double grade) {
        this.grades.add(grade);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", evaluationType='" + evaluationType + '\'' +
                ", grades=" + grades +
                '}';
    }
}
