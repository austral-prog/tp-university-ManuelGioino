package com.university.entity.Grading;

public class OralExam extends Evaluation{

    public OralExam(String name, String studentName,String subjectName, String evaluationType) {
        super(name, studentName, subjectName,evaluationType);
    }

    @Override
    public double getGrade() {
        return getGrades().getFirst();
    }

}
