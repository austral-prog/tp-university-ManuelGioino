package com.university.CLI;

import java.util.Scanner;

public interface CRUDRepository<T extends Entity> {

    void create(T entity);

    T read(int id);

    void update(int id, T entity);

    void delete(int id);

    String getIdentifier();

    Class<T> getEntityClass();

    // Nuevos métodos CLI
    T createEntityFromCLI(Scanner scanner);

    T updateEntityFromCLI(Scanner scanner, T existingEntity);
}
