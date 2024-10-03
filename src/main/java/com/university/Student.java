package com.university;

public class Student {
    private String fullName;
    private int totalCourses;
    private String contactEmail;

    public Student(String fullName, int totalCourses, String contactEmail) {
        this.fullName = fullName;
        this.totalCourses = totalCourses;
        this.contactEmail = contactEmail;
    }

    public String retrieveName() {
        return fullName;
    }

    public int countCourses() {
        return totalCourses;
    }

    public String getEmailAddress() {
        return contactEmail;
    }
}
