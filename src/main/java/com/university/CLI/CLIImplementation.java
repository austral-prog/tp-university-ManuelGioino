package com.university.CLI;

import com.university.Factory.EvaluationCreator;
import com.university.CLI.CRUDRepository;
import com.university.entity.Entity;
import com.university.entity.Classroom.Course;
import com.university.entity.Classroom.Student;
import com.university.entity.Grading.Evaluation;

import java.util.Scanner;

public class CLIImplementation implements CLI {

    @Override
    public void runCLI(CRUDRepository<?>[] crudRepositories) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Select entity type: ");
            for (int i = 0; i < crudRepositories.length; i++) {
                System.out.println((i + 1) + ". " + crudRepositories[i].getIdentifier());
            }
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            if (choice == 0) {
                running = false;
                continue;
            }
            if (choice < 1 || choice > crudRepositories.length) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            CRUDRepository<? extends Entity> repository = crudRepositories[choice - 1];
            handleEntityOperations(scanner, repository);
        }
        scanner.close();
    }

    private <T extends Entity> void handleEntityOperations(Scanner scanner, CRUDRepository<T> repository) {
        System.out.println("Selected: " + repository.getIdentifier());
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Back");
        int operation = scanner.nextInt();

        try {
            switch (operation) {
                case 1 -> createEntity(scanner, repository);
                case 2 -> readEntity(scanner, repository);
                case 3 -> updateEntity(scanner, repository);
                case 4 -> deleteEntity(scanner, repository);
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid operation. Please try again.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private <T extends Entity> void createEntity(Scanner scanner, CRUDRepository<T> repository) {
        System.out.println("Enter details for new " + repository.getIdentifier() + ":");
        T entity = repository.createEntityFromCLI(scanner);
        repository.create(entity);
        System.out.println("Created: " + entity);
    }

    private <T extends Entity> void readEntity(Scanner scanner, CRUDRepository<T> repository) {
        System.out.println("Enter ID to read:");
        int id = scanner.nextInt();
        T entity = repository.read(id);
        if (entity != null) {
            System.out.println("Read: " + entity);
        } else {
            System.out.println("Entity with ID " + id + " not found.");
        }
    }

    private <T extends Entity> void updateEntity(Scanner scanner, CRUDRepository<T> repository) {
        System.out.println("Enter ID to update:");
        int id = scanner.nextInt();
        T entity = repository.read(id);
        if (entity == null) {
            System.out.println("Entity with ID " + id + " not found.");
            return;
        }
        System.out.println("Enter new details for " + repository.getIdentifier() + ":");
        T updatedEntity = repository.updateEntityFromCLI(scanner, entity);
        repository.update(id, updatedEntity);
        System.out.println("Updated: " + updatedEntity);
    }

    private <T extends Entity> void deleteEntity(Scanner scanner, CRUDRepository<T> repository) {
        System.out.println("Enter ID to delete:");
        int id = scanner.nextInt();
        repository.delete(id);
        System.out.println("Deleted entity with ID: " + id);
    }
}
