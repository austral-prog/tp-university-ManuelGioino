package com.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class CourseCounter {

    public Map<String, Integer> countCourses(List<String[]> data) {
        Map<String, Set<String>> studentToCoursesMap = new HashMap<>();

        for (String[] row : data) {
            String student = row[2].trim();
            String course = row[1].trim();

            studentToCoursesMap.computeIfAbsent(student, k -> new HashSet<>()).add(course);
        }

        Map<String, Integer> studentCourseCountMap = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : studentToCoursesMap.entrySet()) {
            studentCourseCountMap.put(entry.getKey(), entry.getValue().size());
        }

        return studentCourseCountMap;
    }
}
