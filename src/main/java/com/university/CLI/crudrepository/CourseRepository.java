package com.university.CLI.crudrepository;

import com.university.entity.Classroom.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseRepository implements CRUDRepository<Course> {
    private final Map<Integer, Course> courseRepo = new HashMap<>();

    @Override
    public void create(Course entity) {
        courseRepo.put(entity.getId(), entity);
    }

    @Override
    public Course read(int id) {
        return courseRepo.get(id);
    }

    @Override
    public void update(int id, Course entity) {
        courseRepo.put(id, entity);
    }

    @Override
    public void delete(int id) {
        courseRepo.remove(id);
    }

    @Override
    public String getIdentifier() {
        return "Course";
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    public Map<Integer, Course> getRepo() {
        return courseRepo;
    }
}