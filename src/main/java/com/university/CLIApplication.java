package com.university;

import com.university.CLI.CLI;
import com.university.CLI.CLIImplementation;
import com.university.Repository.StudentRepository;
import com.university.CLI.CRUDRepository;

public class CLIApplication {
    public static void main(String[] args) {
        CLI cli = new CLIImplementation();

        // Repositorios para cada entidad que será gestionada en el CLI
        CRUDRepository<?>[] repositories = new CRUDRepository<?>[]{
                new StudentRepository()
                // Puedes agregar aquí más repositorios como CourseRepository, EvaluationRepository, etc.
        };

        // Ejecuta el CLI
        cli.runCLI(repositories);
    }
}
