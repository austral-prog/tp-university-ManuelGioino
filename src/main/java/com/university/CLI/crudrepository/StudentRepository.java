package com.university.CLI.crudrepository;

import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository implements CRUDRepository<Student> {
    private final Map<Integer, Student> studentRepo = new HashMap<>();

    @Override
    public void create(Student entity) {
        studentRepo.put(entity.getId(), entity);
    }

    @Override
    public Student read(int id) {
        return studentRepo.get(id);
    }

    @Override
    public void update(int id, Student entity) {
        studentRepo.put(id, entity);
    }

    @Override
    public void delete(int id) {
        studentRepo.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Student";
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    public Map<Integer, Student> getRepo() {
        return studentRepo;
    }
}