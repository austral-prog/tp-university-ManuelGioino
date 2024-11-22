package com.university;

import com.university.CLI.CLIImplementation;

import com.university.CLI.crudrepository.CRUDRepository;
import com.university.CLI.crudrepository.CourseRepository;
import com.university.CLI.crudrepository.EvaluationRepository;
import com.university.CLI.crudrepository.StudentRepository;

public class CLIApplication {
    public static void main(String[] args) {
        CLIImplementation cli = new CLIImplementation();
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EvaluationRepository evaluationRepository = new EvaluationRepository();
        CRUDRepository<?>[] repositories = {studentRepository, courseRepository, evaluationRepository};
        cli.runCLI(repositories);
    }

}