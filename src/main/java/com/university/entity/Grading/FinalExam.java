package com.university.entity.Grading;

public class FinalExam extends Evaluation {

    public FinalExam(String name, String studentName,String subjectName, String evaluationType) {
        super(name, studentName, subjectName,evaluationType);
    }
    public double getGrade(){
        double sum = 0;
        for (int i = 0; i < this.getGrades().size(); i++){
            sum += this.getGrades().get(i);
        }
        return sum;
    }
}