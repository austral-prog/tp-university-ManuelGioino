package com.university;

public class Teacher {
    private String fullName;
    private String subject;
    private int identifier;

    public Teacher(String fullName, String subject, int identifier) {
        this.fullName = fullName;
        this.subject = subject;
        this.identifier = identifier;
    }

    public String retrieveFullName() {
        return fullName;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getSubject() {
        return subject;
    }
}
